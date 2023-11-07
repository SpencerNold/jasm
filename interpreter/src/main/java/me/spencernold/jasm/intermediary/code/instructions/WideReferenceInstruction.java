package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class WideReferenceInstruction extends Instruction {
	
	private int index;
	private int arg0, arg1;

	public WideReferenceInstruction(int opcode) {
		super(opcode);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getArgument0() {
		return arg0;
	}
	
	public void setArgument0(int arg0) {
		this.arg0 = arg0;
	}
	
	public int getArgument1() {
		return arg1;
	}
	
	public void setArgument1(int arg1) {
		this.arg1 = arg1;
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
