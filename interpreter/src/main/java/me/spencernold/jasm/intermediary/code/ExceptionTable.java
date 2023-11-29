package me.spencernold.jasm.intermediary.code;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

/**
 * Encoder and Decoder for JVM exceptions thrown by a method.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.code.JException
 */
public class ExceptionTable implements ReadWriteable<ByteBuf> {

	private final LinkedList<JException> exceptions = new LinkedList<>();

	/**
	 * Gets a linked list of the exceptions which can be thrown by the method.
	 * 
	 * @return LinkedList of decoded JException objects
	 */
	public LinkedList<JException> getExceptions() {
		return exceptions;
	}

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
