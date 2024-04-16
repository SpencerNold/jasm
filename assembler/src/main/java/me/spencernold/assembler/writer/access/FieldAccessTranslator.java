package me.spencernold.assembler.writer.access;

import java.util.ArrayList;
import java.util.List;

import static me.spencernold.jasm.Opcodes.*;

public class FieldAccessTranslator implements AccessTranslator {
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
        if ((access & ACC_VOLATILE) == ACC_VOLATILE)
            modifiers.add("volatile");
        if ((access & ACC_TRANSIENT) == ACC_TRANSIENT)
            modifiers.add("transient");
        if ((access & ACC_SYNTHETIC) == ACC_SYNTHETIC)
            modifiers.add("synthetic");
        if ((access & ACC_ENUM) == ACC_ENUM)
            modifiers.add("enum");
        return String.join(" ", modifiers);
    }
}
