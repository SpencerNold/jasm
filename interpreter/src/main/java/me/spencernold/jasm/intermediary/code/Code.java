package me.spencernold.jasm.intermediary.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public class Code implements ReadWriteable {
	
	private static final Map<Integer, Function<ByteBuf, Instruction>> INSTRUCTION_DECODERS = new HashMap<>();

	static {
		
	}
	
	private static void defineDecoder(int opcode, Class<? extends Instruction> clazz) {
		INSTRUCTION_DECODERS.put(opcode, buf -> {
			try {
				Instruction instruction = clazz.newInstance();
				instruction.read(buf);
				return instruction;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		});
	}
	
	private final List<Instruction> instructions = new LinkedList<>();
	
	@Override
	public void read(ByteBuf buf) {
		System.out.println("Bruh: " + buf.readByte());
		while (!buf.isEmpty()) {
			Instruction insn = new Instruction(); // Make this be the proper instruction to the proper opcode
			insn.read(buf);
			instructions.add(insn);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		
	}
}
