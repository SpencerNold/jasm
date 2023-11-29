package me.spencernold.jasm.intermediary.pools;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class MethodPool implements ReadWriteable<ByteBuf> {

	private final JClass jclass;
	private final LinkedList<JMethod> methods = new LinkedList<>();
	
	public MethodPool(JClass jclass) {
		this.jclass = jclass;
	}
	
	/**
	 * @return LinkedList of decoded methods
	 */
	public LinkedList<JMethod> getMethods() {
		return methods;
	}
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
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
}
