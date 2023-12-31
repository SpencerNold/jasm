package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class VarInstruction extends Instruction {

	private int value;
	
	public VarInstruction(int opcode) {
		super(opcode);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public void read(ByteBuf buf) {
		value = buf.readByte();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(value);
	}
	
	@Override
	public int getSize() {
		return 1;
	}
}
