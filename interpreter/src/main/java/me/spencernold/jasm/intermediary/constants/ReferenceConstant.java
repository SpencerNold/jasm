package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;

/**
 * Represents class, string, and method_type constants which reference another
 * constant in the constant pool (single wide).
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.constants.WideReferenceConstant
 */
public class ReferenceConstant extends Constant {

	private int index;

	public ReferenceConstant(int tag, int index) {
		super(tag);
		this.index = index;
	}

	/**
	 * @return constant pool index being referenced
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index new constant pool reference index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	public <T extends Constant> T getConstant(JClass jclass, Class<T> clazz) {
		Constant constant = jclass.getConstPool().get(index);
		if (!clazz.isAssignableFrom(constant.getClass()))
			throw new IllegalStateException("constant is not of desired type");
		return clazz.cast(constant);
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
	}
}
