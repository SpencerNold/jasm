package me.spencernold.jasm.intermediary.attributes;

import me.spencernold.jasm.ByteBuf;

/**
 * Constant Value attribute class.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 */
public class ConstValueAttribute implements Body {

	private int constValueIndex;

	/**
	 * Sets an index into the constant pool which is represented by this attribute.
	 * The bounds of this constant are not automatically checked by this method.
	 * 
	 * @param constValueIndex new index of the constant in the constant pool
	 */
	public void setConstValueIndex(int constValueIndex) {
		this.constValueIndex = constValueIndex;
	}

	/**
	 * Returns an index into the constant pool which is represented by this
	 * attribute.
	 * 
	 * @return index of the constant in the constant pool
	 */
	public int getConstValueIndex() {
		return constValueIndex;
	}

	@Override
	public void read(ByteBuf buf) {
		constValueIndex = buf.readShort();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(constValueIndex);
	}
}
