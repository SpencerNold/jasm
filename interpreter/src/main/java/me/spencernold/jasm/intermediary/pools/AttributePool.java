package me.spencernold.jasm.intermediary.pools;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.Body;

public class AttributePool implements ReadWriteable {

	private final JClass jclass;
	private final LinkedList<Attribute> attributes = new LinkedList<>();
	
	public AttributePool(JClass jclass) {
		this.jclass = jclass;
	}
	
	public <T extends Body> T getAttributeOf(Class<T> clazz) {
		for (Attribute attr : attributes) {
			if (attr.isInstanceOf(clazz))
				return attr.getBody(clazz);
		}
		return null;
	}
	
	public LinkedList<Attribute> getAttributes() {
		return attributes;
	}
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		for (int i = 0; i < size; i++) {
			Attribute attribute = new Attribute(jclass);
			attribute.read(buf);
			attributes.add(attribute);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(attributes.size());
		for (Attribute attribute : attributes)
			attribute.write(buf);
	}
}
