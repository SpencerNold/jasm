package me.spencernold.jasm.intermediary.constants;

import java.nio.charset.StandardCharsets;

import me.spencernold.jasm.ByteBuf;

/**
 * Represents UTF-8 character sets in the constant pool, and is not the same as
 * a String constant.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.constants.ReferenceConstant
 */
public class Utf8Constant extends Constant {

	private String value;

	public Utf8Constant(int tag, String value) {
		super(tag);
		this.value = value;
	}

	/**
	 * Gets the String value stored in the UTF-8 constant.
	 * 
	 * @return string value of the constant
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the String value to be stored in the UTF-8 constant.
	 * 
	 * @param value new string value of the constant
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(value.length());
		buf.write(value.getBytes(StandardCharsets.UTF_8));
	}
}
