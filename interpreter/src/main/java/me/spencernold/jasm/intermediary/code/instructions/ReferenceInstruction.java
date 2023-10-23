package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class ReferenceInstruction extends Instruction {

	private int index;
	
	public ReferenceInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public void read(ByteBuf buf) {
		index = buf.readShort();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
	}
	
	@Override
	public int getSize() {
		return 2;
	}
}
