package me.spencernold.jasm.intermediary;

import me.spencernold.jasm.ByteBuf;

public interface ReadWriteable {

	public void read(ByteBuf buf);
	public void write(ByteBuf buf);
}
