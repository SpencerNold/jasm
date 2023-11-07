package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class NewMultiArrayInstruction extends Instruction {

	private int index;
	private int dimensions;
	
	public NewMultiArrayInstruction(int opcode) {
		super(opcode);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getDimensions() {
		return dimensions;
	}
	
	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
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
