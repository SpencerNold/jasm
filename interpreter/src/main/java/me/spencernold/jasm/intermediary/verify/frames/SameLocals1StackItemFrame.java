package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.verify.Stack;

public class SameLocals1StackItemFrame extends Frame {
	
	private Stack stack;
	
	public SameLocals1StackItemFrame(int type) {
		super(type);
		stack = new Stack();
	}

	@Override
	public void read(ByteBuf buf) {
		stack.setSize(1);
		stack.read(buf);
	}
	
	@Override
	public void write(ByteBuf buf) {
		stack.write(buf);
	}
}
