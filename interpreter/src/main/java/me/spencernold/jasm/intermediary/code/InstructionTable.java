package me.spencernold.jasm.intermediary.code;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class InstructionTable implements ReadWriteable {
	
	private final Code code;
	
	public InstructionTable() {
		code = new Code();
	}
	
	@Override
	public void read(ByteBuf buf) {
		int length = buf.readInt();
		ByteBuf buf0 = new ByteBuf(buf.read(length));
		code.read(buf0);
	}

	@Override
	public void write(ByteBuf buf) {
		ByteBuf buf0 = new ByteBuf();
		code.write(buf0);
		byte[] data = buf.getRawBuffer();
		buf.writeInt(data.length);
		buf.write(data);
	}
}
