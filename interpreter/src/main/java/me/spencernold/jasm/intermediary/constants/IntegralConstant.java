package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

/**
 * 32-bit integral constant, can represent bytes, shorts, ints, and
 * floats in the constant pool.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.constants.WideIntegralConstant
 */
public class IntegralConstant extends Constant {

	private int value;

	public IntegralConstant(int tag, int value) {
		super(tag);
		this.value = value;
	}

	/**
	 * Represents 8-bit, 16-bit, and 32-bit integers.
	 * 
	 * @return integer value of the constant
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Represents 32-bit floating pointer numbers.
	 * 
	 * @return floating point value of the constant
	 */
	public float getFloatingPointValue() {
		return Float.intBitsToFloat(value);
	}

	/**
	 * Sets the value to a 32-bit floating point number.
	 * 
	 * @param value new value of the constant
	 */
	public void setFloatingPointValue(float value) {
		this.value = Float.floatToIntBits(value);
	}

	/**
	 * Sets the value to an integer, 8-bit, 16-bit, or 32-bit
	 * 
	 * @param value new value of the constant
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeInt(value);
	}
}
