package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.intermediary.pools.ConstPool;

import java.util.Map;
import java.util.HashMap;

public class ReferenceInstruction extends Instruction {
	private int index;
	
	public ReferenceInstruction(int opcode) {
		super(opcode);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void read(ByteBuf buf) {
		index = buf.readShort();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
	}
	
	@Override
	public int getSize() {
		return 2;
	}
}
