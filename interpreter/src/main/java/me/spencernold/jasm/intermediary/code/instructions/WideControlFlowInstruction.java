package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class WideControlFlowInstruction extends Instruction {
	
	private int branch;

	public WideControlFlowInstruction(int opcode) {
		super(opcode);
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
