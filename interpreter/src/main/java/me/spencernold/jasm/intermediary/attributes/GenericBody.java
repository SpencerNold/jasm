package me.spencernold.jasm.intermediary.attributes;

import me.spencernold.jasm.ByteBuf;

/**
 * Generic Attribute body implementation. The JVM specification allows for
 * attributes which are not defined by the JVM specification, and this class
 * implementation preserves those attributes.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 */
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
