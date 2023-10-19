package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

public class IntegralConstant extends Constant {

	private int value;
	
	public IntegralConstant(int tag, int value) {
		super(tag);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeInt(value);
	}
}
