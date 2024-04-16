package me.spencernold.jasm.intermediary.pools;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class InterfacePool implements ReadWriteable<ByteBuf>, Iterable<Short> {

	private ArrayList<Short> interfaces;

	/**
	 * Returns an array of reference indices to the class names of the interfaces in
	 * the constant pool.
	 * 
	 * @return array of reference indices
	 */
	public Short[] getInterfaces() {
		return interfaces.toArray(new Short[0]);
	}

	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		interfaces = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			interfaces.add((short) buf.readShort());
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(interfaces.size());
		for (short s : interfaces)
			buf.writeShort(s);
	}

	@Override
	public Iterator<Short> iterator() {
		return interfaces.iterator();
	}
}
