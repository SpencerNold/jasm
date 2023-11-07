package me.spencernold.jasm.intermediary.verify.locals;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;

public class UninitializedVariableInfo extends VariableInfo {

	private int offset;
	
	public UninitializedVariableInfo() {
		super(Opcodes.SMT_TAG_UNINITIALIZED);
	}

	@Override
	public void read(ByteBuf buf) {
		offset = buf.readShort();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(offset);
	}
}
