package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

/**
 * 64-bit integral constant, can represent longs and doubles in the constant
 * pool.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.constants.IntegralConstant
 */
public class WideIntegralConstant extends Constant {

	private long value;

	public WideIntegralConstant(int tag, long value) {
		super(tag);
		this.value = value;
	}

	/**
	 * Gets the double-wide integer constant value.
	 * 
	 * @return 64-bit integer of the stored constant
	 */
	public long getValue() {
		return value;
	}

	/**
	 * Sets the double-wide integer constant value.
	 * 
	 * @param value new 64-bit integer to be stored in the constant
	 */
	public void setValue(long value) {
		this.value = value;
	}

	/**
	 * Gets the double-wide floating point constant value.
	 * 
	 * @return 64-bit floating point number of the stored constant
	 */
	public double getFloatingPointValue() {
		return Double.longBitsToDouble(value);
	}

	/**
	 * Sets the double-wide floating point constant value.
	 * 
	 * @param value new 64-bit floating point number to be stored in the constant
	 */
	public void setFloatingPointValue(double value) {
		this.value = Double.doubleToLongBits(value);
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeLong(value);
	}
}
