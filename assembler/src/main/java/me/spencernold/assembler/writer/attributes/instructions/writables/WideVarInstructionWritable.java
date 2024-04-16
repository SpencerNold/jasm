package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.WideVarInstruction;

public class WideVarInstructionWritable extends InstructionWritable {

    public WideVarInstructionWritable(String instructionTextName) {
        super(instructionTextName);
    }

    @Override
    public void write(StringWriter writer, Instruction instruction) {
        writer.printf("%s #%d\n", instructionTextName, ((WideVarInstruction) instruction).getValue());
    }
}
