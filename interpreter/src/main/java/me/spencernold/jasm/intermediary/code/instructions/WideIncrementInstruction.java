package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class WideIncrementInstruction extends Instruction {
	
	private int index;
	private int constant;

	public WideIncrementInstruction(int opcode) {
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
		index = buf.readShort();
		constant = buf.readShort();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
		buf.writeShort(constant);
	}

	@Override
	public int getSize() {
		return 4;
	}
}
