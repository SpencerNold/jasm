package me.spencernold.assembler.writer.attributes.instructions;

import me.spencernold.assembler.writer.InputJWritable;
import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public abstract class InstructionWritable implements InputJWritable<StringWriter, Instruction> {

    protected final String instructionTextName;

    public InstructionWritable(String instructionTextName) {
        this.instructionTextName = instructionTextName;
    }
}
