package me.spencernold.assembler.writer.access;

import java.util.ArrayList;
import java.util.List;

import static me.spencernold.jasm.Opcodes.*;

public class ClassAccessTranslator implements AccessTranslator {
    @Override
    public String translate(int access) {
        List<String> modifiers = new ArrayList<>();
        if ((access & ACC_PUBLIC) == ACC_PUBLIC)
            modifiers.add("public");
        if ((access & ACC_FINAL) == ACC_FINAL)
            modifiers.add("final");
        if ((access & ACC_SUPER) == ACC_SUPER)
            modifiers.add("super");
        if ((access & ACC_INTERFACE) == ACC_INTERFACE)
            modifiers.add("interface");
        if ((access & ACC_ABSTRACT) == ACC_ABSTRACT)
            modifiers.add("abstract");
        if ((access & ACC_SYNTHETIC) == ACC_SYNTHETIC)
            modifiers.add("synthetic");
        if ((access & ACC_ANNOTATION) == ACC_ANNOTATION)
            modifiers.add("annotation");
        if ((access & ACC_ENUM) == ACC_ENUM)
            modifiers.add("enum");
        if ((access & ACC_MODULE) == ACC_MODULE)
            modifiers.add("module");
        return String.join(" ", modifiers);
    }
}
