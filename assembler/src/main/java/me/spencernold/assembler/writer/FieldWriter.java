package me.spencernold.assembler.writer;

import me.spencernold.assembler.writer.access.AccessTranslator;
import me.spencernold.jasm.intermediary.JField;
import me.spencernold.jasm.intermediary.JWritable;
import me.spencernold.jasm.intermediary.pools.ConstPool;
import me.spencernold.jasm.intermediary.pools.FieldPool;

public class FieldWriter implements JWritable<StringWriter> {

    private final AccessTranslator accessTranslator = new AccessTranslator.Factory(AccessTranslator.Type.FIELD).create();
    private final ConstPool constPool;
    private final FieldPool fieldPool;

    public FieldWriter(ConstPool constPool, FieldPool fieldPool) {
        this.constPool = constPool;
        this.fieldPool = fieldPool;
    }

    @Override
    public void write(StringWriter writer) {
        boolean verbose = writer.hasVerboseFlag();
        writer.println("fields {");
        writer.incrementTabCount();
        boolean isFirstIndex = true;
        for (JField field : fieldPool) {
            if (!isFirstIndex)
                writer.println();
            if (verbose)
                writer.printf("// %s %s %s\n", accessTranslator.translate(field.getAccess()), field.getDescriptor(), field.getName());
            writer.printf("access: 0x%04x\n", field.getAccess());
            writer.println("name: #" + field.getNameIndex());
            writer.println("desc: #" + field.getDescriptorIndex());
            AttributeWriter attributeWriter = new AttributeWriter(constPool, field.getAttributePool());
            attributeWriter.write(writer);
            isFirstIndex = false;
        }
        writer.decrementTabCount();
        writer.println("}\n");
    }
}
