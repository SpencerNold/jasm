package me.spencernold.jasm.intermediary.code;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.pools.AttributePool;

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
	
	public int getMaxStack() {
		return maxStack;
	}
	
	public void setMaxStack(int maxStack) {
		this.maxStack = maxStack;
	}
	
	public int getMaxLocals() {
		return maxLocals;
	}
	
	public void setMaxLocals(int maxLocals) {
		this.maxLocals = maxLocals;
	}
	
	public InstructionTable getInstructions() {
		return instructions;
	}
	
	public ExceptionTable getExceptions() {
		return exceptions;
	}
	
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
