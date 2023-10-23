package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class IncrementInstruction extends Instruction {

	private int index, constant;
	
	public IncrementInstruction(int opcode) {
		super(opcode);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getConstant() {
		return constant;
	}
	
	public void setConstant(int constant) {
		this.constant = constant;
	}

	@Override
	public void read(ByteBuf buf) {
		index = buf.readByte();
		constant = buf.readByte();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(index);
		buf.writeByte(constant);
	}
	
	@Override
	public int getSize() {
		return 2;
	}
}
