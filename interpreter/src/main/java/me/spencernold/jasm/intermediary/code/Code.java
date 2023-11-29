package me.spencernold.jasm.intermediary.code;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

/**
 * Reader and Writer for encoding and decoding JVM instructions
 * 
 * @author Spencer Nold
 * @since 1.0.0
 *
 */
public class Code implements ReadWriteable<ByteBuf> {

	private final LinkedList<Instruction> instructions = new LinkedList<>();
	private final InsnEncoder decoder = new InsnEncoder();

	/**
	 * Gets a LinkedList of the JVM instructions in the Code attribute.
	 * Implementations of {@code Instruction} may include opcodes as well as other
	 * parameters.
	 * 
	 * @see https://docs.oracle.com/javase/specs/jvms/se21/jvms21.pdf
	 * @see https://en.wikipedia.org/wiki/List_of_Java_bytecode_instructions
	 * 
	 * @return a LinkedList of decoded Instructions
	 */
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
