package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

public class ReferenceConstant extends Constant {

	private int index;
	
	public ReferenceConstant(int tag, int index) {
		super(tag);
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
	}
}
