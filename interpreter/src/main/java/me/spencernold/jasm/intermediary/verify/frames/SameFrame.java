package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;

public class SameFrame extends Frame {

	public SameFrame(int type) {
		super(type);
	}

	@Override
	public void read(ByteBuf buf) {
	}

	@Override
	public void write(ByteBuf buf) {
	}
}
