package me.spencernold.jasm.intermediary.constants;

import java.nio.charset.StandardCharsets;

import me.spencernold.jasm.ByteBuf;

public class Utf8Constant extends Constant {

	private String value;
	
	public Utf8Constant(int tag, String value) {
		super(tag);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(value.length());
		buf.write(value.getBytes(StandardCharsets.UTF_8));
	}
}
