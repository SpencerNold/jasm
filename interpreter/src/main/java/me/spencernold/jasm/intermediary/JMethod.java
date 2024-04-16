package me.spencernold.jasm.intermediary;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.attributes.impl.CodeAttribute;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.Utf8Constant;
import me.spencernold.jasm.intermediary.pools.AttributePool;

import java.util.Collection;
import java.util.LinkedList;

public class JMethod implements AttributeElement, ReadWriteable<ByteBuf> {

	private final JClass jclass;
	private int access;
	private int nameIndex;
	private int descriptorIndex;
	private final AttributePool attributePool;

	public JMethod(JClass jclass) {
		this.jclass = jclass;
		attributePool = new AttributePool(jclass);
	}

	/**
	 * Gets the access modifier of the method.
	 * 
	 * @return integer value of the access modifier
	 */
	public int getAccess() {
		return access;
	}

	/**
	 * Sets the access modifier of the method.
	 * 
	 * @param access new integer value of the access modifier
	 */
	public void setAccess(int access) {
		this.access = access;
	}

	/**
	 * Gets the index of the method name in the constant pool.
	 * 
	 * @return index of method name in constant pool
	 */
	public int getNameIndex() {
		return nameIndex;
	}

	/**
	 * Sets the index of the method name in the constant pool.
	 * 
	 * @param nameIndex new method name index in constant pool
	 */
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	/**
	 * Gets the value of the method name in the constant pool.
	 * 
	 * @return utf8 string of the name
	 */
	public String getName() {
		Constant constant = jclass.getConstPool().get(nameIndex);
		if (!(constant instanceof Utf8Constant) || !constant.isUtf8())
			throw new ClassFormatException(Type.MALFORMED, "name is not a string");
		return ((Utf8Constant) constant).getValue();
	}

	/**
	 * Gets the index of the method descriptor in the constant pool.
	 * 
	 * @return index of method descriptor in constant pool
	 */
	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	/**
	 * Sets the index of the method descriptor in the constant pool.
	 * 
	 * @param descriptorIndex new index of method descriptor in constant pool
	 */
	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}
	
	/**
	 * Gets the value of the method descriptor in the constant pool.
	 * 
	 * @return utf8 string of the descriptor
	 */
	public String getDescriptor() {
		Constant constant = jclass.getConstPool().get(descriptorIndex);
		if (!(constant instanceof Utf8Constant) || !constant.isUtf8())
			throw new ClassFormatException(Type.MALFORMED, "descriptor is not a string");
		return ((Utf8Constant) constant).getValue();
	}

	/**
	 * Gets an instance of the method's attribute pool.
	 * 
	 * @return AttributePool instance for the method
	 */
	public AttributePool getAttributePool() {
		return attributePool;
	}

	/**
	 * Gets a linked list of all the instructions in this method. Shorthand and
	 * functionally equivalent to
	 * {@code getAttributePool().getAttributeOf(CodeAttribute.class).getInstructions().getCode().getInstructions()}
	 * 
	 * @return
	 */
	public LinkedList<Instruction> getInstructions() {
		return getAttributePool().getAttributeOf(CodeAttribute.class).getInstructions().getCode().getInstructions();
	}

	public void setInstructions(Collection<Instruction> instructions) {
		LinkedList<Instruction> linkedList;
		if (instructions instanceof LinkedList)
			linkedList = (LinkedList<Instruction>) instructions;
		else
            linkedList = new LinkedList<>(instructions);
		getAttributePool().getAttributeOf(CodeAttribute.class).getInstructions().getCode().setInstructions(linkedList);
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
