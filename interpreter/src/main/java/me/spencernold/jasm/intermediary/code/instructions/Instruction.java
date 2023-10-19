package me.spencernold.jasm.intermediary.code.instructions;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class Instruction implements ReadWriteable {
	
	// field insn
	// method insn
	// jmp insn
	// load insn
	// load_w insn
	// type insn
	// var insn
	
	private int opcode;
	
	public Instruction() {}
	
	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}
	
	public int getOpcode() {
		return opcode;
	}

	@Override
	public void read(ByteBuf buf) {
		opcode = buf.readByte();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeByte(opcode);
	}
}
