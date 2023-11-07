package me.spencernold.jasm.intermediary.verify.frames;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.verify.Locals;

public class AppendFrame extends Frame {
	
	private int offsetDelta;
	private Locals locals;

	public AppendFrame(int type) {
		super(type);
		this.locals = new Locals();
	}
	
	@Override
	public void read(ByteBuf buf) {
		offsetDelta = buf.readShort();
		int size = getType() - 251;
		locals.setSize(size);
		locals.read(buf);
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(offsetDelta);
		locals.write(buf);
	}
}
