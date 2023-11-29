package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class WideVarInstruction extends Instruction {
	
	private int value;

	public WideVarInstruction(int opcode) {
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
		value = buf.readShort();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(value);
	}
	
	@Override
	public int getSize() {
		return 4;
	}
}
