package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class LookupSwitchInstruction extends Instruction {

	public LookupSwitchInstruction(int opcode) {
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
		return 0; // TODO
	}
}
