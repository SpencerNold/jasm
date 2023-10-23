package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class GenericInstruction extends Instruction {

	public GenericInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public void read(ByteBuf buf) {
	}

	@Override
	public void write(ByteBuf buf) {
	}
	
	@Override
	public int getSize() {
		return 0;
	}
}
