package me.spencernold.jasm.intermediary.code;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public class Code implements ReadWriteable {
	
	private final LinkedList<Instruction> instructions = new LinkedList<>();
	private final InsnEncoder decoder = new InsnEncoder();
	
	public LinkedList<Instruction> getInstructions() {
		return instructions;
	}
	
	@Override
	public void read(ByteBuf buf) {
		while (!buf.isEmpty())
			instructions.add(decoder.decode(buf));
	}

	@Override
	public void write(ByteBuf buf) {
		for (Instruction insn : instructions)
			decoder.encode(buf, insn);
	}
}
