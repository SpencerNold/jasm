package me.spencernold.jasm.intermediary;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.pools.AttributePool;

public class JMethod implements ReadWriteable {

	private int access;
	private int nameIndex;
	private int descriptorIndex;
	private final AttributePool attributePool;
	
	public JMethod(JClass jclass) {
		attributePool = new AttributePool(jclass);
	}
	
	public int getAccess() {
		return access;
	}
	
	public void setAccess(int access) {
		this.access = access;
	}
	
	public int getNameIndex() {
		return nameIndex;
	}
	
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public int getDescriptorIndex() {
		return descriptorIndex;
	}
	
	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}
	
	public AttributePool getAttributePool() {
		return attributePool;
	}
	
	@Override
	public void read(ByteBuf buf) {
		access = buf.readShort();
		nameIndex = buf.readShort();
		descriptorIndex = buf.readShort();
		attributePool.read(buf);
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(access);
		buf.writeShort(nameIndex);
		buf.writeShort(descriptorIndex);
		attributePool.write(buf);
	}
}
