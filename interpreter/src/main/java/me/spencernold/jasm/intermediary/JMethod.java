package me.spencernold.jasm.intermediary;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.CodeAttribute;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.pools.AttributePool;

public class JMethod implements ReadWriteable {

	private int access;
	private int nameIndex;
	private int descriptorIndex;
	private final AttributePool attributePool;

	public JMethod(JClass jclass) {
		attributePool = new AttributePool(jclass);
	}

	/**
	 * Gets the access modifier of the field.
	 * 
	 * @return integer value of the access modifier
	 */
	public int getAccess() {
		return access;
	}

	/**
	 * Sets the access modifier of the field.
	 * 
	 * @param access new integer value of the access modifier
	 */
	public void setAccess(int access) {
		this.access = access;
	}

	/**
	 * Gets the index of the field name in the constant pool.
	 * 
	 * @return index of field name in constant pool
	 */
	public int getNameIndex() {
		return nameIndex;
	}

	/**
	 * Sets the index of the field name in the constant pool.
	 * 
	 * @param nameIndex new field name index in constant pool
	 */
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	/**
	 * Gets the index of the field descriptor in the constant pool.
	 * 
	 * @return index of field descriptor in constant pool
	 */
	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	/**
	 * Sets the index of the field descriptor in the constant pool.
	 * 
	 * @param descriptorIndex new index of field descriptor in constant pool
	 */
	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}

	/**
	 * Gets an instance of the field's attribute pool.
	 * 
	 * @return AttributePool instance for the field
	 */
	public AttributePool getAttributePool() {
		return attributePool;
	}

	/**
	 * Gets a linked list of all of the instructions in this method. Shorthand and
	 * functionally equivalent to
	 * {@code getAttributePool().getAttributeOf(CodeAttribute.class).getInstructions().getCode().getInstructions()}
	 * 
	 * @return
	 */
	public LinkedList<Instruction> getInstructions() {
		return getAttributePool().getAttributeOf(CodeAttribute.class).getInstructions().getCode().getInstructions();
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
