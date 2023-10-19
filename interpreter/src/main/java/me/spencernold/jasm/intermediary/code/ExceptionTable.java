package me.spencernold.jasm.intermediary.code;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class ExceptionTable implements ReadWriteable {

	private final LinkedList<JException> exceptions = new LinkedList<>();
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		for (int i = 0; i < size; i++) {
			JException exception = new JException();
			exception.read(buf);
			exceptions.add(exception);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(exceptions.size());
		for (JException exception : exceptions)
			exception.write(buf);
	}
}
