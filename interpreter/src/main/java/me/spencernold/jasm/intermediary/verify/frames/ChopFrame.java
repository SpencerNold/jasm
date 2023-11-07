package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;

public class ChopFrame extends Frame {
	
	private int offsetDelta;

	public ChopFrame(int type) {
		super(type);
	}

	@Override
	public void read(ByteBuf buf) {
		offsetDelta = buf.readShort();
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(offsetDelta);
	}
}
