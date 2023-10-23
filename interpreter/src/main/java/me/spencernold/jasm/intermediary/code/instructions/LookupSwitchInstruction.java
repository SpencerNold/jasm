package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class LookupSwitchInstruction extends Instruction {
	
	private int padding;
	private int def;
	private int npairs;
	private int[] offsets;

	public LookupSwitchInstruction(int opcode) {
		super(opcode);
	}
	
	@Override
	public void setWithOffset(int offset) {
		padding = (4 - (offset % 4)) % 4;
	}

	@Override
	public void read(ByteBuf buf) {
		buf.read(padding);
		def = buf.readInt();
		npairs = buf.readInt();
		int size = npairs * 2;
		offsets = new int[size];
		for (int i = 0; i < size; i++)
			offsets[i] = buf.readInt();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.write(new byte[padding]);
		buf.writeInt(def);
		buf.writeInt(npairs);
		for (int i : offsets)
			buf.writeInt(i);
	}
	
	@Override
	public int getSize() {
		return padding + (8 * npairs) + 8;
	}
}
