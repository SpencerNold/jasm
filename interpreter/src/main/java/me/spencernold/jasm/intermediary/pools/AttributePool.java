package me.spencernold.jasm.intermediary.pools;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.Body;

/**
 * Stores attributes of a method, field, class, or another attributes.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 */
public class AttributePool implements ReadWriteable {

	private final JClass jclass;
	private final LinkedList<Attribute> attributes = new LinkedList<>();

	public AttributePool(JClass jclass) {
		this.jclass = jclass;
	}

	/**
	 * Finds an attribute body of type <T> in the pool of attributes, casts it to
	 * type <T> and returns it.
	 * 
	 * @param <T>   type of the attribute to get
	 * @param clazz class of the target attribute type
	 * @return an instance of a attribute body with type <T>
	 */
	public <T extends Body> T getAttributeOf(Class<T> clazz) {
		for (Attribute attr : attributes) {
			if (attr.isInstanceOf(clazz))
				return attr.getBody(clazz);
		}
		return null;
	}

	/**
	 * @return LinkedList of the attributes in the pool
	 */
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
