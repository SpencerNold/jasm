package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.intermediary.ReadWriteable;

public abstract class Instruction implements ReadWriteable {
	
	protected int opcode;
	
	public Instruction(int opcode) {
		this.opcode = opcode;
	}
	
	public int getOpcode() {
		return opcode;
	}
	
	public void setWithOffset(int offset) {}
	
	public abstract int getSize();
}
