package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;

public abstract class Constant {

	protected int tag;

	public Constant(int tag) {
		this.tag = tag;
	}
	
	public int getTag() {
		return tag;
	}
	
	public boolean isClass() {
		return tag == Opcodes.CONSTANT_CLASS;
	}
	
	public boolean isFieldRef() {
		return tag == Opcodes.CONSTANT_FIELD_REF;
	}
	
	public boolean isMethodRef() {
		return tag == Opcodes.CONSTANT_METHOD_REF;
	}
	
	public boolean isInterfaceMethodRef() {
		return tag == Opcodes.CONSTANT_INTERFACE_METHOD_REF;
	}
	
	public boolean isString() {
		return tag == Opcodes.CONSTANT_STRING;
	}
	
	public boolean isInteger() {
		return tag == Opcodes.CONSTANT_INTEGER;
	}
	
	public boolean isFloat() {
		return tag == Opcodes.CONSTANT_FLOAT;
	}
	
	public boolean isLong() {
		return tag == Opcodes.CONSTANT_LONG;
	}
	
	public boolean isDouble() {
		return tag == Opcodes.CONSTANT_DOUBLE;
	}
	
	public boolean isNameAndType() {
		return tag == Opcodes.CONSTANT_NAME_AND_TYPE;
	}
	
	public boolean isUtf8() {
		return tag == Opcodes.CONSTANT_UTF8;
	}
	
	public boolean isMethodHandle() {
		return tag == Opcodes.CONSTANT_METHOD_HANDLE;
	}
	
	public boolean isMethodType() {
		return tag == Opcodes.CONSTANT_METHOD_TYPE;
	}
	
	public boolean isInvokeDynamic() {
		return tag == Opcodes.CONSTANT_INVOKE_DYNAMIC;
	}
	
	public abstract void write(ByteBuf buf);
}
