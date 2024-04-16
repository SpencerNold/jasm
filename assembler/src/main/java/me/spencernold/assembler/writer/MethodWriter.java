package me.spencernold.assembler.writer;

import me.spencernold.assembler.writer.access.AccessTranslator;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.JWritable;
import me.spencernold.jasm.intermediary.pools.ConstPool;
import me.spencernold.jasm.intermediary.pools.MethodPool;

public class MethodWriter implements JWritable<StringWriter> {

    private final AccessTranslator accessTranslator = new AccessTranslator.Factory(AccessTranslator.Type.METHOD).create();
    private final ConstPool constPool;
    private final MethodPool methodPool;

    public MethodWriter(ConstPool constPool, MethodPool methodPool) {
        this.constPool = constPool;
        this.methodPool = methodPool;
    }

    @Override
    public void write(StringWriter writer) {
        boolean verbose = writer.hasVerboseFlag();
        writer.println("methods {");
        writer.incrementTabCount();
        boolean isFirstIndex = true;
        for (JMethod method : methodPool) {
            if (!isFirstIndex)
                writer.println();
            if (verbose)
                writer.printf("// %s %s%s\n", accessTranslator.translate(method.getAccess()), method.getName(), method.getDescriptor());
            writer.printf("access: 0x%04x\n", method.getAccess());
            writer.println("name: #" + method.getNameIndex());
            writer.println("desc: #" + method.getDescriptorIndex());
            AttributeWriter attributeWriter = new AttributeWriter(constPool, method.getAttributePool());
            attributeWriter.write(writer);
            isFirstIndex = false;
        }
        writer.decrementTabCount();
        writer.println("}\n");
    }
}
