package me.spencernold.assembler.writer.attributes.instructions.writables;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionTableWriter;
import me.spencernold.assembler.writer.attributes.instructions.InstructionWritable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.WideInstruction;

public class WideInstructionWritable extends InstructionWritable {

    private final InstructionTableWriter writer;

    public WideInstructionWritable(InstructionTableWriter writer) {
        super(null);
        this.writer = writer;
    }


    @Override
    public void write(StringWriter writer, Instruction instruction) {
        instruction = ((WideInstruction) instruction).getChild();
        writer.printf("wide ");
        this.writer.writables.get(instruction.getOpcode()).write(writer, instruction);
    }
}
