package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.verify.StackMapTable;

/**
 * StackMapTable attribute class. For encoding and decoding the verification
 * frames of a StackMapTable.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.verify.StackMapTable
 */
public class StackMapTableAttribute implements Body {

	private final StackMapTable stackMapTable;

	public StackMapTableAttribute() {
		stackMapTable = new StackMapTable();
	}

	/**
	 * Returns an instance of a StackMapTable implementation
	 * 
	 * @return instance of the StackMapTable
	 */
	public StackMapTable getStackMapTable() {
		return stackMapTable;
	}

	@Override
	public void read(ByteBuf buf) {
		stackMapTable.read(buf);
	}

	@Override
	public void write(ByteBuf buf) {
		stackMapTable.write(buf);
	}
}
