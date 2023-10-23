package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class TableSwitchInstruction extends Instruction {
	
	private int padding;
	private int def, low, high;
	private int[] offsets;

	public TableSwitchInstruction(int opcode) {
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
		low = buf.readInt();
		high = buf.readInt();
		int size = high - low + 1;
		offsets = new int[size];
		for (int i = 0; i < size; i++)
			offsets[i] = buf.readInt();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.write(new byte[padding]);
		buf.writeInt(def);
		buf.writeInt(low);
		buf.writeInt(high);
		for (int i : offsets)
			buf.writeInt(i);
	}
	
	@Override
	public int getSize() {
		return padding + (offsets.length * 4) + 12;
	}
}
