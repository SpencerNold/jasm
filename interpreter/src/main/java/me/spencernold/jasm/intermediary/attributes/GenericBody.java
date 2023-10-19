package me.spencernold.jasm.intermediary.attributes;

import me.spencernold.jasm.ByteBuf;

public class GenericBody implements Body {
	
	private byte[] data;

	@Override
	public void read(ByteBuf buf) {
		data = buf.getRawBuffer();
		buf.setRawBuffer(new byte[0]);
	}

	@Override
	public void write(ByteBuf buf) {
		buf.write(data);
	}
}
