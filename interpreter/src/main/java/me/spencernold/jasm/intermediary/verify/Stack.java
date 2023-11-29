package me.spencernold.jasm.intermediary.verify;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.verify.locals.GenericVariableInfo;
import me.spencernold.jasm.intermediary.verify.locals.ObjectVariableInfo;
import me.spencernold.jasm.intermediary.verify.locals.UninitializedVariableInfo;
import me.spencernold.jasm.intermediary.verify.locals.VariableInfo;

public class Stack implements ReadWriteable<ByteBuf> {
	
	private final LinkedList<VariableInfo> verificationTypeInfo = new LinkedList<>();
	private int size;
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}

	@Override
	public void read(ByteBuf buf) {
		for (int i = 0; i < size; i++) {
			int tag = buf.readByte();
			VariableInfo varInfo = null;
			switch (tag) {
			case Opcodes.SMT_TAG_OBJECT:
				varInfo = new ObjectVariableInfo();
				break;
			case Opcodes.SMT_TAG_UNINITIALIZED:
				varInfo = new UninitializedVariableInfo();
				break;
			default:
				varInfo = new GenericVariableInfo(tag);
				break;
			}
			varInfo.read(buf);
			verificationTypeInfo.add(varInfo);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		for (VariableInfo varInfo : verificationTypeInfo) {
			buf.writeByte(varInfo.getType());
			varInfo.write(buf);
		}
	}
}
