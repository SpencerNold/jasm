package me.spencernold.assembler.writer.attributes;

import me.spencernold.assembler.writer.AttributeWriter;
import me.spencernold.assembler.writer.InputJWritable;
import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionTableWriter;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.CodeAttribute;
import me.spencernold.jasm.intermediary.pools.ConstPool;

public class CodeAttrWriter implements InputJWritable<StringWriter, Body> {

    private final ConstPool constPool;

    public CodeAttrWriter(ConstPool constPool) {
        this.constPool = constPool;
    }

    @Override
    public void write(StringWriter writer, Body body) {
        writer.incrementTabCount();
        CodeAttribute attribute = (CodeAttribute) body;
        writer.println("stack: " + attribute.getMaxStack());
        writer.println("locals: " + attribute.getMaxLocals());
        InstructionTableWriter instructionWriter = new InstructionTableWriter(constPool, attribute.getInstructions().getCode());
        instructionWriter.write(writer);
        // TODO Implement exception table stuff? I'll have to look at the documentation for what causes this to have a non-zero size
        AttributeWriter attributeWriter = new AttributeWriter(constPool, attribute.getAttributePool());
        attributeWriter.write(writer);
        writer.decrementTabCount();
    }
}
