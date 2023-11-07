package me.spencernold.jasm.intermediary;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.ReferenceConstant;
import me.spencernold.jasm.intermediary.constants.Utf8Constant;
import me.spencernold.jasm.intermediary.pools.AttributePool;
import me.spencernold.jasm.intermediary.pools.ConstPool;
import me.spencernold.jasm.intermediary.pools.FieldPool;
import me.spencernold.jasm.intermediary.pools.InterfacePool;
import me.spencernold.jasm.intermediary.pools.MethodPool;

/**
 * Instance of a decoded but still raw binary class file.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.ClassReader
 * @see me.spencernold.jasm.ClassWriter
 */
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

	/**
	 * Finds the string name of the class in the constant pool.
	 * 
	 * @return name of the class file
	 */
	public String getClassName() {
		Constant constant = constPool.get(classNameIndex);
		if (!constant.isClass() || !(constant instanceof ReferenceConstant))
			throw new ClassFormatException(Type.MALFORMED, "classNameIndex is not of the class constant type");
		return ((ReferenceConstant) constant).getConstant(this, Utf8Constant.class).getValue();
	}

	/**
	 * Finds the string name of the super class in the constant pool.
	 * 
	 * @return name of the super class
	 */
	public String getSuperName() {
		Constant constant = constPool.get(superNameIndex);
		if (!constant.isClass() || !(constant instanceof ReferenceConstant))
			throw new ClassFormatException(Type.MALFORMED, "classNameIndex is not of the class constant type");
		return ((ReferenceConstant) constant).getConstant(this, Utf8Constant.class).getValue();
	}

	/**
	 * @return LinkedList of the fields in the class
	 */
	public LinkedList<JField> getFields() {
		return fieldPool.getFields();
	}

	/**
	 * @return LinkedList of the methods in the class
	 */
	public LinkedList<JMethod> getMethods() {
		return methodPool.getMethods();
	}

	/**
	 * Gets the version in which this class file was compiled in. The JVM supports
	 * backwards compatibility, but newer classes may not be run on older JVM
	 * implementations.
	 * 
	 * @return version of the class
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version in which this class file was compiled in. The JVM supports
	 * backwards compatibility, but newer classes may not be run on older JVM
	 * implementations.
	 * 
	 * @param version new version of the class
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Constants in a class are stored in what is known as a constant pool. This
	 * function gets the decoded values stored in the constant pool.
	 * 
	 * @return constant pool instance
	 */
	public ConstPool getConstPool() {
		return constPool;
	}

	/**
	 * Gets the access modifiers of the class. For most class files, this will just
	 * be public ({@code Opcodes.ACC_PUBLIC}, but may differ depending on if it is
	 * final or an inner class.
	 * 
	 * @return access modifier of the class
	 */
	public int getAccess() {
		return access;
	}

	/**
	 * Sets the access modifiers of the class. For most class files, this will just
	 * be public ({@code Opcodes.ACC_PUBLIC}, but may differ depending on if it is
	 * final or an inner class.
	 * 
	 * @param access new access modifier of the class
	 */
	public void setAccess(int access) {
		this.access = access;
	}

	/**
	 * Gets the constant pool index of the name of the class file.
	 * 
	 * @return constant pool index of the class name
	 */
	public int getClassNameIndex() {
		return classNameIndex;
	}

	/**
	 * Sets the constant pool index of the name of the class file.
	 * 
	 * @param classNameIndex new index of the class name in the constant pool
	 */
	public void setClassNameIndex(int classNameIndex) {
		this.classNameIndex = classNameIndex;
	}

	/**
	 * Gets the constant pool index of the name of the super class.
	 * 
	 * @return constant pool index of the super class name
	 */
	public int getSuperNameIndex() {
		return superNameIndex;
	}

	/**
	 * Sets the constant pool index of the name of the super class.
	 * 
	 * @param superNameIndex new index of the class name in the constant pool
	 */
	public void setSuperNameIndex(int superNameIndex) {
		this.superNameIndex = superNameIndex;
	}

	/**
	 * @return instance of the interface pool
	 */
	public InterfacePool getInterfacePool() {
		return interfacePool;
	}

	/**
	 * @return instance of the field pool
	 */
	public FieldPool getFieldPool() {
		return fieldPool;
	}

	/**
	 * @return instance of the method pool
	 */
	public MethodPool getMethodPool() {
		return methodPool;
	}

	/**
	 * @return instance of the attribute pool
	 */
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
