package me.spencernold.llvm;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JField;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.utils.Pair;
import me.spencernold.llvm.binding.Function;
import me.spencernold.llvm.binding.LLVM;
import me.spencernold.llvm.binding.Module;
import me.spencernold.llvm.binding.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ClassObjectCompiler {

    private final JavaCompiler compiler;
    private final JClass jclass;
    private final Module module;
    private final String qualifiedClassName;

    private final HashMap<String, Pair<JMethod, Function>> functions = new HashMap<>();

    public ClassObjectCompiler(JavaCompiler compiler, JClass jclass) {
        this.compiler = compiler;
        this.jclass = jclass;
        this.module = LLVM.INSTANCE.createModule(qualifiedClassName = jclass.getClassName().replace('/', '_'));
    }

    public void process() {
        // Declare the class as a struct
        // Add class fields to the struct
        // Add methods, static methods are just normal functions
        // class methods include an instance of the struct
        // Recursively add all parent class fields as struct fields, same with methods to flatten out the structure

        List<Type> fields = new ArrayList<>();
        for (JField field : jclass.getFields())
            fields.add(Type.getFromDescriptor(module, field.getDescriptor())[0]);
        module.setFields(fields.toArray(new Type[0]));

        for (JMethod method : jclass.getMethods()) {
            String name = qualifiedClassName + "_" + method.getName() + method.getDescriptor();
            Type[] types = Type.getFromDescriptor(module, method.getDescriptor());
            Function function = module.createFunction(name, method.getAccess(), types[types.length - 1], Arrays.copyOfRange(types, 0, types.length - 1));
            functions.put(name, new Pair<>(method, function));
        }
    }

    public void compile() {
        // add bodies to declared functions in the process phase
        for (Pair<JMethod, Function> pair : functions.values()) {
            JMethod method = pair.getKey();
            Function function = pair.getValue();

            InstructionTableDecoder decoder = new InstructionTableDecoder(module, function, method);
            function.startBody();
            // most primitive types are represented with an i32 in Java
            // for NOW, the machine code will as well
            // in the future, jasm will have a stronger type system for optimizations
            decoder.start();
            for (Instruction instruction : method.getInstructions())
                decoder.update(instruction);
            function.finish();
        }
    }

    public void build() {
        module.write();
    }

    public void close() {
        module.free();
    }
}
