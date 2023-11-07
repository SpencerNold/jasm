package me.spencernold.jasm.intermediary.verify.locals;

import me.spencernold.jasm.ByteBuf;

public class GenericVariableInfo extends VariableInfo {

	public GenericVariableInfo(int type) {
		super(type);
	}
	
	@Override
	public void read(ByteBuf buf) {
	}
	
	@Override
	public void write(ByteBuf buf) {
	}
}
