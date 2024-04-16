package me.spencernold.jasm.intermediary.pools;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.ReadWriteable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MethodPool implements ReadWriteable<ByteBuf>, Iterable<JMethod> {

	private final JClass jclass;
	private ArrayList<JMethod> methods;
	public MethodPool(JClass jclass) {
		this.jclass = jclass;
	}
	
	/**
	 * @return LinkedList of decoded methods
	 */
	public ArrayList<JMethod> getMethods() {
		return methods;
	}
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		methods = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			JMethod method = new JMethod(jclass);
			method.read(buf);
			methods.add(method);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(methods.size());
		for (JMethod method : methods)
			method.write(buf);
	}

	@Override
	public Iterator<JMethod> iterator() {
		return methods.iterator();
	}
}
