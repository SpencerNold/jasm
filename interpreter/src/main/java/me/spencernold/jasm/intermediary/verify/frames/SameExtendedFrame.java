package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;

public class SameExtendedFrame extends Frame {

	private int offsetDelta;
	
	public SameExtendedFrame(int type) {
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
