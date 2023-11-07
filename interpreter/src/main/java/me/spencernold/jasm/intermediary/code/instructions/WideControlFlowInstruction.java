package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class WideControlFlowInstruction extends Instruction {
	
	private int branch;

	public WideControlFlowInstruction(int opcode) {
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
		branch = buf.readInt();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeInt(branch);
	}
	
	@Override
	public int getSize() {
		return 4;
	}
}
