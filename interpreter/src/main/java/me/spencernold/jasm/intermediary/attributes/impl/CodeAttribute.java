package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.code.ExceptionTable;
import me.spencernold.jasm.intermediary.code.InstructionTable;
import me.spencernold.jasm.intermediary.pools.AttributePool;

/**
 * Attribute class which is referencing the Code of a method.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.intermediary.code.Code
 */
public class CodeAttribute implements Body {

	private int maxStack;
	private int maxLocals;
	private final InstructionTable instructions;
	private final ExceptionTable exceptions;
	private final AttributePool attributePool;

	public CodeAttribute(JClass jclass) {
		this.instructions = new InstructionTable();
		this.exceptions = new ExceptionTable();
		this.attributePool = new AttributePool(jclass);
	}

	/**
	 * Gets the max stack size of the method which the attribute is attached to.
	 * 
	 * @return max stack size of the method
	 */
	public int getMaxStack() {
		return maxStack;
	}

	/**
	 * Sets the max stack size of the method which the attribute is attached to.
	 * 
	 * @param maxStack new max stack size of the method
	 */
	public void setMaxStack(int maxStack) {
		this.maxStack = maxStack;
	}

	/**
	 * Gets the max lcoals count of the method which the attribute is attached to.
	 * 
	 * @return locals size of the method
	 */
	public int getMaxLocals() {
		return maxLocals;
	}

	/**
	 * Sets the max loclas count of the method which the attribute is attached to.
	 * 
	 * @param maxLocals new max locals count of the method
	 */
	public void setMaxLocals(int maxLocals) {
		this.maxLocals = maxLocals;
	}

	/**
	 * Gets the instruction table of the method, the decoded byte opcodes of the
	 * method.
	 * 
	 * @return instance of the attribute's instruction table
	 */
	public InstructionTable getInstructions() {
		return instructions;
	}

	/**
	 * Gets the exception table of the method, the decoded bytes which reference
	 * thrown exceptions.
	 * 
	 * @return instance of the attribute's exception table
	 */
	public ExceptionTable getExceptions() {
		return exceptions;
	}

	/**
	 * Gets the attribute pool of this attribute.
	 * 
	 * @return instance of the attribute's attributes
	 */
	public AttributePool getAttributePool() {
		return attributePool;
	}

	@Override
	public void read(ByteBuf buf) {
		maxStack = buf.readShort();
		maxLocals = buf.readShort();
		instructions.read(buf);
		exceptions.read(buf);
		attributePool.read(buf);
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(maxStack);
		buf.writeShort(maxLocals);
		instructions.write(buf);
		exceptions.write(buf);
		attributePool.write(buf);
	}
}
