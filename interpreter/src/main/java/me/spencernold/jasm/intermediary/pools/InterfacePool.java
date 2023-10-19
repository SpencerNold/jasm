package me.spencernold.jasm.intermediary.pools;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class InterfacePool implements ReadWriteable {
	
	private final LinkedList<Short> interfaces = new LinkedList<>();

	public Short[] getInterfaces() {
		return interfaces.toArray(new Short[interfaces.size()]);
	}
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		for (int i = 0; i < size; i++)
			interfaces.add(buf.readShort());
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(interfaces.size());
		for (short s : interfaces)
			buf.writeShort(s);
	}
}
