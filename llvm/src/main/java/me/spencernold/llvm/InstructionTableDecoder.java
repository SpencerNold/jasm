package me.spencernold.llvm;

import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.PushInstruction;
import me.spencernold.llvm.binding.Function;
import me.spencernold.llvm.binding.Module;
import me.spencernold.llvm.binding.Operation;
import me.spencernold.llvm.binding.Value;
import me.spencernold.llvm.binding.operations.IntegralConstant;
import me.spencernold.llvm.utils.DescriptorTool;

import java.util.ArrayList;
import java.util.Stack;

import static me.spencernold.jasm.Opcodes.*;

public class InstructionTableDecoder {

    private final Module module;
    private final Function function;
    private final JMethod method;

    private final Stack<Operation> stack = new Stack<>();
    private final Value[] localsPointers;

    public InstructionTableDecoder(Module module, Function function, JMethod method) {
        this.module = module;
        this.function = function;
        this.method = method;
        localsPointers = new Value[method.getMaxLocals()];
    }

    public void start() {
        ArrayList<DescriptorTool.Type> types = DescriptorTool.getDescriptorTypeList(method.getDescriptor());
        int target = 0;
        for (int i = 0; i < types.size() - 1; i++) {
            localsPointers[target] = function.getArgumentRef(i);
            DescriptorTool.Type type = types.get(i);
            switch (type) {
                case LONG:
                case DOUBLE:
                    target++;
                    break;
                default:
                    break;
            }
            target++;
        }
    }

    public void update(Instruction instruction) {
        switch (instruction.getOpcode()) {
            case ICONST_0:
                stack.push(new IntegralConstant(0));
                break;
            case ISTORE_1:
                Value pointer = findOrAllocateInt(function, 1, false);
                Value value = stack.pop().getAsValue(module);
                function.storeI32(pointer, value);
                break;
            case BIPUSH:
                stack.push(new IntegralConstant(((PushInstruction) instruction).getValue()));
                break;
            case RETURN:
                function.addReturn(module.getNullValue());
                break;
        }
    }

    private Value findOrAllocateInt(Function function, int index, boolean wide) {
        Value value = localsPointers[index];
        if (value == null) {
            value = wide ? function.allocateI64() : function.allocateI32();
            localsPointers[index] = value;
        }
        return value;
    }
}
