package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

public class WideIntegralConstant extends Constant {

	private long value;
	
	public WideIntegralConstant(int tag, long value) {
		super(tag);
		this.value = value;
	}

	public long getValue() {
		return value;
	}
	
	public void setValue(long value) {
		this.value = value;
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeLong(value);
	}
}
