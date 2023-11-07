package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.verify.Locals;
import me.spencernold.jasm.intermediary.verify.Stack;

public class FullFrame extends Frame {
	
	private int offsetDelta;
	private Locals locals;
	private Stack stack;

	public FullFrame(int type) {
		super(type);
		this.locals = new Locals();
		this.stack = new Stack();
	}

	@Override
	public void read(ByteBuf buf) {
		offsetDelta = buf.readShort();
		int numberOfLocals = buf.readShort();
		locals.setSize(numberOfLocals);
		locals.read(buf);
		int numberOfStackItems = buf.readShort();
		stack.setSize(numberOfStackItems);
		stack.read(buf);
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(offsetDelta);
		buf.writeShort(locals.getSize());
		locals.write(buf);
		buf.writeShort(stack.getSize());
		stack.write(buf);
	}
}
