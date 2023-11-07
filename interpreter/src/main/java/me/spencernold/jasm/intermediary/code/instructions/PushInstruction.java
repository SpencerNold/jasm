package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;

public class PushInstruction extends Instruction {

	private int value;
	
	public PushInstruction(int opcode) {
		super(opcode);
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void read(ByteBuf buf) {
		if (opcode == Opcodes.BIPUSH)
			value = buf.readByte();
		else if (opcode == Opcodes.SIPUSH)
			value = buf.readShort();
		else
			throw new ClassFormatException(Type.MALFORMED, "invalid push instruction opcode: %s", opcode);
	}
	
	@Override
	public void write(ByteBuf buf) {
		if (opcode == Opcodes.BIPUSH)
			buf.writeByte(value);
		else if (opcode == Opcodes.SIPUSH)
			buf.writeShort(value);
		else
			throw new ClassFormatException(Type.MALFORMED, "invalid push instruction opcode: %s", opcode);
	}
	
	@Override
	public int getSize() {
		if (opcode == Opcodes.BIPUSH)
			return 1;
		else if (opcode == Opcodes.SIPUSH)
			return 2;
		return 0;
	}
}
