package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public class GenericInstructionWritable extends InstructionWritable {

    public GenericInstructionWritable(String instructionTextName) {
        super(instructionTextName);
    }

    @Override
    public void write(StringWriter writer, Instruction instruction) {
        writer.println(instructionTextName);
    }
}
