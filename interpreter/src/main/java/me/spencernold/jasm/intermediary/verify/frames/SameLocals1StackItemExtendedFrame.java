package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.verify.Stack;

public class SameLocals1StackItemExtendedFrame extends Frame {
	
	private int offsetDelta;
	private Stack stack;

	public SameLocals1StackItemExtendedFrame(int type) {
		super(type);
		this.stack = new Stack();
	}

	@Override
	public void read(ByteBuf buf) {
		offsetDelta = buf.readShort();
		stack.setSize(1);
		stack.read(buf);
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(offsetDelta);
		stack.write(buf);
	}
}
