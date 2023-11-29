package me.spencernold.jasm.intermediary.verify;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.verify.frames.Frame;

public class StackMapTable implements ReadWriteable<ByteBuf> {

	private final LinkedList<Frame> frames = new LinkedList<>();
	private final FrameEncoder encoder = new FrameEncoder();
	
	@Override
	public void read(ByteBuf buf) {
		int count = buf.readShort();
		for (int i = 0; i < count; i++) {
			Frame frame = encoder.decode(buf);
			frame.read(buf);
			frames.add(frame);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(frames.size());
		for (Frame frame : frames)
			encoder.encode(frame, buf);
	}
}
