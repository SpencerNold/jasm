package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

public class WideReferenceConstant extends Constant {

	private int index1, index2;
	
	public WideReferenceConstant(int tag, int index1, int index2) {
		super(tag);
		this.index1 = index1;
		this.index2 = index2;
	}
	
	public int getIndex1() {
		return index1;
	}
	
	public void setIndex1(int index1) {
		this.index1 = index1;
	}
	
	public int getIndex2() {
		return index2;
	}
	
	public void setIndex2(int index2) {
		this.index2 = index2;
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index1);
		buf.writeShort(index2);
	}
}
