package me.spencernold.assembler.writer.access;

import java.util.ArrayList;
import java.util.List;

import static me.spencernold.jasm.Opcodes.*;

public class MethodAccessTranslator implements AccessTranslator {
    @Override
    public String translate(int access) {
        List<String> modifiers = new ArrayList<>();
        if ((access & ACC_PUBLIC) == ACC_PUBLIC)
            modifiers.add("public");
        if ((access & ACC_PRIVATE) == ACC_PRIVATE)
            modifiers.add("private");
        if ((access & ACC_PROTECTED) == ACC_PROTECTED)
            modifiers.add("protected");
        if ((access & ACC_STATIC) == ACC_STATIC)
            modifiers.add("static");
        if ((access & ACC_FINAL) == ACC_FINAL)
            modifiers.add("final");
        if ((access & ACC_SYNCHRONIZED) == ACC_SYNCHRONIZED)
            modifiers.add("synchronized");
        if ((access & ACC_BRIDGE) == ACC_BRIDGE)
            modifiers.add("bridge");
        if ((access & ACC_VARARGS) == ACC_VARARGS)
            modifiers.add("varargs");
        if ((access & ACC_NATIVE) == ACC_NATIVE)
            modifiers.add("native");
        if ((access & ACC_ABSTRACT) == ACC_ABSTRACT)
            modifiers.add("abstract");
        if ((access & ACC_STRICT) == ACC_STRICT)
            modifiers.add("strict");
        if ((access & ACC_SYNTHETIC) == ACC_SYNTHETIC)
            modifiers.add("synthetic");
        return String.join(" ", modifiers);
    }
}
