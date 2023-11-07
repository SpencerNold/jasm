package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;

public class WideInstruction extends Instruction {
	
	private Instruction child;

	public WideInstruction(int opcode) {
		super(opcode);
	}
	
	public Instruction getChild() {
		return child;
	}
	
	public void setChild(Instruction child) {
		this.child = child;
	}

	@Override
	public void read(ByteBuf buf) {
		int opcode = buf.readByte();
		if (opcode == Opcodes.IINC)
			child = new WideIncrementInstruction(opcode);
		else
			child = new ReferenceInstruction(opcode);
		child.read(buf);
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(child.getOpcode());
		child.write(buf);
	}

	@Override
	public int getSize() {
		return 1 + child.getSize();
	}
}
