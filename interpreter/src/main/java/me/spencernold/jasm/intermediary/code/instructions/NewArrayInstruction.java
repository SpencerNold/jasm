package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class NewArrayInstruction extends Instruction {

	private int type;
	
	public NewArrayInstruction(int opcode) {
		super(opcode);
	}
	
	@Override
	public void read(ByteBuf buf) {
		type = buf.readByte();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(type);
	}
	
	@Override
	public int getSize() {
		return 1;
	}
}
