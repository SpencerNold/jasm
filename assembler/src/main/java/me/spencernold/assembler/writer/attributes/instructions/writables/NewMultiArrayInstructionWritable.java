package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.NewMultiArrayInstruction;

public class NewMultiArrayInstructionWritable extends InstructionWritable {

    public NewMultiArrayInstructionWritable(String instructionTextName) {
        super(instructionTextName);
    }

    @Override
    public void write(StringWriter writer, Instruction instruction) {
        NewMultiArrayInstruction newMultiArrayInstruction = (NewMultiArrayInstruction) instruction;
        writer.printf("%s #%d %d\n", instructionTextName, newMultiArrayInstruction.getIndex(), newMultiArrayInstruction.getSize());
    }
}
