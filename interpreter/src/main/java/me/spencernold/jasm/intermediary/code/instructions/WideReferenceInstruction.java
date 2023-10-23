package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class WideReferenceInstruction extends Instruction {
	
	private int index;
	private int arg0, arg1;

	public WideReferenceInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public void read(ByteBuf buf) {
		index = buf.readShort();
		arg0 = buf.readByte();
		arg1 = buf.readByte();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
		buf.writeByte(arg0);
		buf.writeByte(arg1);
	}
	
	@Override
	public int getSize() {
		return 4;
	}
}
