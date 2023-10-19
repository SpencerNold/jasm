package me.spencernold.jasm.intermediary;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.pools.AttributePool;
import me.spencernold.jasm.intermediary.pools.ConstPool;
import me.spencernold.jasm.intermediary.pools.FieldPool;
import me.spencernold.jasm.intermediary.pools.InterfacePool;
import me.spencernold.jasm.intermediary.pools.MethodPool;

public class JClass implements ReadWriteable {

	private int version;
	private final ConstPool constPool;
	private int access;
	private int classNameIndex;
	private int superNameIndex;
	private final InterfacePool interfacePool;
	private final FieldPool fieldPool;
	private final MethodPool methodPool;
	private final AttributePool attributePool;
	
	public JClass() {
		constPool = new ConstPool();
		interfacePool = new InterfacePool();
		fieldPool = new FieldPool(this);
		methodPool = new MethodPool(this);
		attributePool = new AttributePool(this);
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public ConstPool getConstPool() {
		return constPool;
	}
	
	public int getAccess() {
		return access;
	}
	
	public void setAccess(int access) {
		this.access = access;
	}
	
	public int getClassNameIndex() {
		return classNameIndex;
	}
	
	public void setClassNameIndex(int classNameIndex) {
		this.classNameIndex = classNameIndex;
	}
	
	public int getSuperNameIndex() {
		return superNameIndex;
	}
	
	public void setSuperNameIndex(int superNameIndex) {
		this.superNameIndex = superNameIndex;
	}
	
	public InterfacePool getInterfacePool() {
		return interfacePool;
	}
	
	public FieldPool getFieldPool() {
		return fieldPool;
	}
	
	public MethodPool getMethodPool() {
		return methodPool;
	}
	
	public AttributePool getAttributePool() {
		return attributePool;
	}
	
	@Override
	public void read(ByteBuf buf) {
		if (buf.readInt() != 0xCAFEBABE)
			throw new ClassFormatException(Type.NOT_CLASS);
		version = buf.readInt();
		constPool.read(buf);
		access = buf.readShort();
		classNameIndex = buf.readShort();
		superNameIndex = buf.readShort();
		interfacePool.read(buf);
		fieldPool.read(buf);
		methodPool.read(buf);
		attributePool.read(buf);
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeInt(0xCAFEBABE);
		buf.writeInt(version);
		constPool.write(buf);
		buf.writeShort(access);
		buf.writeShort(classNameIndex);
		buf.writeShort(superNameIndex);
		interfacePool.write(buf);
		fieldPool.write(buf);
		methodPool.write(buf);
		attributePool.write(buf);
	}
}
