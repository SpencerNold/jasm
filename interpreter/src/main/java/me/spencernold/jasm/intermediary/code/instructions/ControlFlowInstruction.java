package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class ControlFlowInstruction extends Instruction {
	
	private int branch;
	
	public ControlFlowInstruction(int opcode) {
		super(opcode);
	}
	
	public int getBranch() {
		return branch;
	}
	
	public void setBranch(int branch) {
		this.branch = branch;
	}

	@Override
	public void read(ByteBuf buf) {
		branch = buf.readShort();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(branch);
	}
	
	@Override
	public int getSize() {
		return 2;
	}
}
