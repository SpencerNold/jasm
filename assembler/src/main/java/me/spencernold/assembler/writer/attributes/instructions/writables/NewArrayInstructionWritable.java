package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.NewArrayInstruction;

public class NewArrayInstructionWritable extends InstructionWritable {

    public NewArrayInstructionWritable(String instructionTextName) {
        super(instructionTextName);
    }

    @Override
    public void write(StringWriter writer, Instruction instruction) {
        writer.printf("%s %d\n", instructionTextName, ((NewArrayInstruction) instruction).getType());
    }
}
