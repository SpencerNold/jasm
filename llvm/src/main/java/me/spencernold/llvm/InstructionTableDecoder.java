package me.spencernold.llvm;

import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.PushInstruction;
import me.spencernold.jasm.intermediary.code.instructions.VarInstruction;
import me.spencernold.llvm.binding.Function;
import me.spencernold.llvm.binding.Module;
import me.spencernold.llvm.binding.NumericType;
import me.spencernold.llvm.binding.Value;
import me.spencernold.llvm.utils.DescriptorTool;

import java.util.ArrayList;
import java.util.Stack;

import static me.spencernold.jasm.Opcodes.*;

public class InstructionTableDecoder {

    private final Module module;
    private final Function function;
    private final JMethod method;

    private final Stack<Value> stack = new Stack<>();
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
            case ARETURN:
            case DRETURN:
            case IRETURN:
                function.addReturn(stack.pop());
                break;
            case SIPUSH:
            case BIPUSH:
                stack.push(module.getI32Constant(((PushInstruction) instruction).getValue()));
                break;
            case D2F:
                stack.push(function.castD2F(stack.pop()));
                break;
            case D2L:
                stack.push(function.castD2L(stack.pop()).setType2(true));
                break;
            case D2I:
                stack.push(function.castD2I(stack.pop()));
                break;
            case DADD:
                stack.push(function.addFloatingPointTypes(stack.pop(), stack.pop()).setType2(true));
                break;
            case DCONST_0:
                stack.push(module.getF64Constant(0).setType2(true));
                break;
            case DCONST_1:
                stack.push(module.getF64Constant(1).setType2(true));
                break;
            case DDIV:
                stack.push(function.divideFloatingPointTypes(stack.pop(), stack.pop()).setType2(true));
                break;
            case DLOAD:
                Value dload_ptr = findOrAllocateNumericLocal(function, ((VarInstruction) instruction).getValue(), NumericType.DOUBLE);
                stack.push(function.load(NumericType.DOUBLE, dload_ptr).setType2(true));
                break;
            case DLOAD_0:
                stack.push(function.load(NumericType.DOUBLE, findOrAllocateNumericLocal(function, 0, NumericType.DOUBLE)).setType2(true));
                break;
            case DLOAD_1:
                stack.push(function.load(NumericType.DOUBLE, findOrAllocateNumericLocal(function, 1, NumericType.DOUBLE)).setType2(true));
                break;
            case DLOAD_2:
                stack.push(function.load(NumericType.DOUBLE, findOrAllocateNumericLocal(function, 2, NumericType.DOUBLE)).setType2(true));
                break;
            case DLOAD_3:
                stack.push(function.load(NumericType.DOUBLE, findOrAllocateNumericLocal(function, 3, NumericType.DOUBLE)).setType2(true));
                break;
            case DMUL:
                stack.push(function.multiplyFloatingPointTypes(stack.pop(), stack.pop()).setType2(true));
                break;
            case DNEG:
                stack.push(function.negateFloatingPointType(stack.pop()).setType2(true));
                break;
            case DREM:
                stack.push(function.modulusFloatingPointTypes(stack.pop(), stack.pop()).setType2(true));
                break;
            case DSTORE:
                Value dstore_ptr = findOrAllocateNumericLocal(function, ((VarInstruction) instruction).getValue(), NumericType.DOUBLE);
                function.store(dstore_ptr, stack.pop());
                break;
            case DSTORE_0:
                function.store(findOrAllocateNumericLocal(function, 0, NumericType.DOUBLE), stack.pop());
                break;
            case DSTORE_1:
                function.store(findOrAllocateNumericLocal(function, 1, NumericType.DOUBLE), stack.pop());
                break;
            case DSTORE_2:
                function.store(findOrAllocateNumericLocal(function, 2, NumericType.DOUBLE), stack.pop());
                break;
            case DSTORE_3:
                function.store(findOrAllocateNumericLocal(function, 3, NumericType.DOUBLE), stack.pop());
                break;
            case DSUB:
                stack.push(function.subtractFloatingPointTypes(stack.pop(), stack.pop()).setType2(true));
                break;
            case DUP:
                stack.push(stack.peek());
                break;
            case DUP_X1:
                Value dupx1_value1 = stack.pop();
                Value dupx1_value2 = stack.pop();
                stack.push(dupx1_value1);
                stack.push(dupx1_value2);
                stack.push(dupx1_value1);
                break;
            case DUP_X2:
                Value dupx2_value1 = stack.pop();
                Value dupx2_value2 = stack.pop();
                Value dupx2_value3 = null;
                if (!dupx2_value2.isType2())
                     dupx2_value3 = stack.pop();
                stack.push(dupx2_value1);
                if (dupx2_value3 != null)
                    stack.push(dupx2_value3);
                stack.push(dupx2_value2);
                stack.push(dupx2_value1);
                break;
            case DUP2:
                Value dup2_value1 = stack.pop();
                if (!dup2_value1.isType2()) {
                    Value dup2_value2 = stack.pop();
                    stack.push(dup2_value2);
                    stack.push(dup2_value1);
                    stack.push(dup2_value2);
                }
                stack.push(dup2_value1);
                if (dup2_value1.isType2())
                    stack.push(dup2_value1);
                break;
            case DUP2_X1:
                Value dup2x1_value1 = stack.pop();
                Value dup2x1_value2 = null;
                if (!dup2x1_value1.isType2())
                    dup2x1_value2 = stack.pop();
                Value dup2x1_valueN = stack.pop();
                if (dup2x1_value2 != null)
                    stack.push(dup2x1_value2);
                stack.push(dup2x1_value1);
                stack.push(dup2x1_valueN);
                if (dup2x1_value2 != null)
                    stack.push(dup2x1_value2);
                stack.push(dup2x1_value1);
                break;
            case DUP2_X2:
                Value dup2x2_value1 = stack.pop();
                Value dup2x2_value2 = null;
                if (!dup2x2_value1.isType2())
                    dup2x2_value2 = stack.pop();
                Value dup2x2_valueN1 = stack.pop();
                Value dup2x2_valueN2 = stack.pop();
                if (dup2x2_value2 != null)
                    stack.push(dup2x2_value2);
                stack.push(dup2x2_value1);
                stack.push(dup2x2_valueN2);
                stack.push(dup2x2_valueN1);
                if (dup2x2_value2 != null)
                    stack.push(dup2x2_value2);
                stack.push(dup2x2_value1);
                break;
            case F2D:
                stack.push(function.castF2D(stack.pop()));
                break;
            case F2I:
                stack.push(function.castF2I(stack.pop()));
                break;
            case F2L:
                stack.push(function.castF2L(stack.pop()));
                break;
            case FADD:
                stack.push(function.addFloatingPointTypes(stack.pop(), stack.pop()));
            case RETURN:
                function.addReturn(module.getNullValue());
                break;
        }
    }

    private Value findOrAllocateNumericLocal(Function function, int index, NumericType type) {
        Value value = localsPointers[index];
        if (value == null) {
            value = function.allocate(type);
            localsPointers[index] = value;
        }
        return value;
    }
}
