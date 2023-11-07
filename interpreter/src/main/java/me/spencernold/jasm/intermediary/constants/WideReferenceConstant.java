package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

/**
 * Represents field_ref, method_ref, interface_method_ref, name_and_type, and
 * invoke_dynamic constants which reference two constants in the constant pool
 * (double wide).
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.constants.ReferenceConstant
 */
public class WideReferenceConstant extends Constant {

	private int index1, index2;

	public WideReferenceConstant(int tag, int index1, int index2) {
		super(tag);
		this.index1 = index1;
		this.index2 = index2;
	}

	/**
	 * @return first index referenced in the constant pool
	 */
	public int getIndex1() {
		return index1;
	}

	/**
	 * @param index1 new first index into the constant pool
	 */
	public void setIndex1(int index1) {
		this.index1 = index1;
	}

	/**
	 * @return second index referenced in the constant pool
	 */
	public int getIndex2() {
		return index2;
	}

	/**
	 * @param index2 new second index into the constant pool
	 */
	public void setIndex2(int index2) {
		this.index2 = index2;
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index1);
		buf.writeShort(index2);
	}
}
