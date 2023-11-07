package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

/**
 * Method handle constant, which contains the type of a method handle as well as
 * an index into the constant pool.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 */
public class MethodHandleConstant extends Constant {

	private int type, index;

	public MethodHandleConstant(int tag, int type, int index) {
		super(tag);
		this.type = type;
		this.index = index;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(type);
		buf.writeShort(index);
	}
}
