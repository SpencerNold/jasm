package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;

public class TableSwitchInstruction extends Instruction {
	
	private int padding;
	private int def, low, high;
	private int[] offsets;

	public TableSwitchInstruction(int opcode) {
		super(opcode);
	}
	
	public int getDefault() {
		return def;
	}
	
	public void setDefault(int def) {
		this.def = def;
	}
	
	public int getLow() {
		return low;
	}
	
	public void setLow(int low) {
		this.low = low;
	}
	
	public int getHigh() {
		return high;
	}
	
	public void setHigh(int high) {
		this.high = high;
	}
	
	public int[] getOffsets() {
		return offsets;
	}
	
	public void setOffsets(int[] offsets) {
		this.offsets = offsets;
	}
	
	public void setOffset(int index, int value) {
		offsets[index] = value;
	}
	
	public int getOffset(int index) {
		return offsets[index];
	}
	
	@Override
	public void setWithOffset(int offset) {
		System.out.println(offset);
		padding = (4 - (offset % 4)) % 4;
		System.out.println("Padding: " + padding);
	}

	@Override
	public void read(ByteBuf buf) {
		buf.read(padding);
		def = buf.readInt();
		low = buf.readInt();
		high = buf.readInt();
		System.out.println("Def " + def);
		System.out.println("Low " + low);
		System.out.println("High " + high);
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
