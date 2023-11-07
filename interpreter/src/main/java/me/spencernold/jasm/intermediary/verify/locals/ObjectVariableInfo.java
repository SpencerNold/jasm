package me.spencernold.jasm.intermediary.verify.locals;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;

public class ObjectVariableInfo extends VariableInfo {

	private int index;
	
	public ObjectVariableInfo() {
		super(Opcodes.SMT_TAG_OBJECT);
	}

	@Override
	public void read(ByteBuf buf) {
		index = buf.readShort();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(index);
	}
}
