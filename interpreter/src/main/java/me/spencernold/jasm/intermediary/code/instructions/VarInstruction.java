package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class VarInstruction extends Instruction {

	private int index;
	
	public VarInstruction(int opcode) {
		super(opcode);
	}
	
	@Override
	public void read(ByteBuf buf) {
		index = buf.readByte();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(index);
	}
	
	@Override
	public int getSize() {
		return 1;
	}
}
