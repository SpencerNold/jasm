package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.ControlFlowInstruction;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public class ControlFlowInstructionWritable extends InstructionWritable {

    public ControlFlowInstructionWritable(String instructionTextName) {
        super(instructionTextName);
    }

    @Override
    public void write(StringWriter writer, Instruction instruction) {
        writer.printf("%s %d\n", instructionTextName, ((ControlFlowInstruction) instruction).getBranch());
    }
}
