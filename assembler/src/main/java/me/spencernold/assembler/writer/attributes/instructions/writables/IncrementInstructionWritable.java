package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.IncrementInstruction;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public class IncrementInstructionWritable extends InstructionWritable {

    public IncrementInstructionWritable(String instructionTextName) {
        super(instructionTextName);
    }

    @Override
    public void write(StringWriter writer, Instruction instruction) {
        IncrementInstruction incrementInstruction = (IncrementInstruction) instruction;
        writer.printf("%s %d, %d\n", instructionTextName, incrementInstruction.getIndex(), incrementInstruction.getConstant());
    }
}
