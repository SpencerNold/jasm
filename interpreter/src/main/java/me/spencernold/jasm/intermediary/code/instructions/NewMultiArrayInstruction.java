package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class NewMultiArrayInstruction extends Instruction {

	private int index;
	private int dimensions;
	
	public NewMultiArrayInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public void read(ByteBuf buf) {
		index = buf.readShort();
		dimensions = buf.readByte();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
		buf.writeByte(dimensions);
	}
	
	@Override
	public int getSize() {
		return 3;
	}
}
