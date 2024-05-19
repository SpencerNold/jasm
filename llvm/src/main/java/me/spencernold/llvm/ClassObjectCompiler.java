package me.spencernold.llvm;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.llvm.binding.LLVM;
import me.spencernold.llvm.binding.Module;

import java.io.Closeable;
import java.io.IOException;

public class ClassObjectCompiler implements Closeable {

    private final JClass jclass;
    private final Module module;

    public ClassObjectCompiler(JClass jclass) {
        this.jclass = jclass;
        this.module = LLVM.INSTANCE.createModule(jclass.getClassName());
    }

    public void build() {
        // Declare the class as a struct
        // Add class fields to the struct
        // Add methods, static methods are just normal functions
        // class methods include an instance of the struct

        module.write();
    }

    @Override
    public void close() throws IOException {
        module.free();
    }
}
