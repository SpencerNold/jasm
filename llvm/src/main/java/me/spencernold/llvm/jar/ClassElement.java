package me.spencernold.llvm.jar;

import me.spencernold.jasm.ClassReader;
import me.spencernold.jasm.intermediary.JClass;

public class ClassElement extends ResourceElement {

    public ClassElement(String name, byte[] body) {
        super(name, body);
    }

    public JClass read() {
        ClassReader reader = new ClassReader(body);
        return reader.read();
    }
}
