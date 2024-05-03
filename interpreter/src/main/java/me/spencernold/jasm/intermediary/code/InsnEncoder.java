package me.spencernold.jasm.intermediary.code;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.DynamicByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.intermediary.code.instructions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class InsnEncoder {

	private final Map<Integer, Function<ByteBuf, Instruction>> decoders = new HashMap<>();
	private int offset;

	@SuppressWarnings("deprecation")
	public InsnEncoder() {
		// Index based instructions
		initialize(Opcodes.ALOAD, VarInstruction.class);
		initialize(Opcodes.ASTORE, VarInstruction.class);
		initialize(Opcodes.DLOAD, VarInstruction.class);
		initialize(Opcodes.DSTORE, VarInstruction.class);
		initialize(Opcodes.FLOAD, VarInstruction.class);
		initialize(Opcodes.FSTORE, VarInstruction.class);
		initialize(Opcodes.ILOAD, VarInstruction.class);
		initialize(Opcodes.ISTORE, VarInstruction.class);
		initialize(Opcodes.LLOAD, VarInstruction.class);
		initialize(Opcodes.LSTORE, VarInstruction.class);
		initialize(Opcodes.LDC, VarInstruction.class);
		initialize(Opcodes.RET, VarInstruction.class);
		initialize(Opcodes.NEWARRAY, VarInstruction.class);
		// load immediates (push)
		initialize(Opcodes.BIPUSH, PushInstruction.class);
		initialize(Opcodes.SIPUSH, PushInstruction.class);
		// 16-bit const pool reference instructions (not jmp insns)
		initialize(Opcodes.ANEWARRAY, ReferenceInstruction.class);
		initialize(Opcodes.CHECKCAST, ReferenceInstruction.class);
		initialize(Opcodes.GETFIELD, ReferenceInstruction.class);
		initialize(Opcodes.GETSTATIC, ReferenceInstruction.class);
		initialize(Opcodes.INSTANCEOF, ReferenceInstruction.class);
		initialize(Opcodes.INVOKESPECIAL, ReferenceInstruction.class);
		initialize(Opcodes.INVOKESTATIC, ReferenceInstruction.class);
		initialize(Opcodes.INVOKEVIRTUAL, ReferenceInstruction.class);
		initialize(Opcodes.LDC_W, ReferenceInstruction.class);
		initialize(Opcodes.LDC2_W, ReferenceInstruction.class);
		initialize(Opcodes.NEW, ReferenceInstruction.class);
		initialize(Opcodes.PUTFIELD, ReferenceInstruction.class);
		initialize(Opcodes.PUTSTATIC, ReferenceInstruction.class);
		// wide reference instruction
		initialize(Opcodes.INVOKEDYNAMIC, WideVarInstruction.class);
		initialize(Opcodes.INVOKEINTERFACE, WideVarInstruction.class);
		// control flow instructions
		initialize(Opcodes.GOTO, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ACMPEQ, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ACMPNE, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ICMPEQ, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ICMPGE, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ICMPGT, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ICMPLE, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ICMPLT, ControlFlowInstruction.class);
		initialize(Opcodes.IF_ICMPNE, ControlFlowInstruction.class);
		initialize(Opcodes.IFEQ, ControlFlowInstruction.class);
		initialize(Opcodes.IFGE, ControlFlowInstruction.class);
		initialize(Opcodes.IFGT, ControlFlowInstruction.class);
		initialize(Opcodes.IFLE, ControlFlowInstruction.class);
		initialize(Opcodes.IFLT, ControlFlowInstruction.class);
		initialize(Opcodes.IFNE, ControlFlowInstruction.class);
		initialize(Opcodes.IFNONNULL, ControlFlowInstruction.class);
		initialize(Opcodes.IFNULL, ControlFlowInstruction.class);
		initialize(Opcodes.JSR, ControlFlowInstruction.class);
		// wide control flow instructions
		initialize(Opcodes.GOTO_W, WideControlFlowInstruction.class);
		initialize(Opcodes.JSR_W, WideControlFlowInstruction.class);
		// other special
		initialize(Opcodes.IINC, IncrementInstruction.class);
		initialize(Opcodes.NEWARRAY, NewArrayInstruction.class);
		initialize(Opcodes.MULTIANEWARRAY, NewMultiArrayInstruction.class);
		initialize(Opcodes.TABLESWITCH, TableSwitchInstruction.class);
		initialize(Opcodes.LOOKUPSWITCH, LookupSwitchInstruction.class);
		initialize(Opcodes.WIDE, WideInstruction.class);
	}

	/**
	 * Reads the opcode as an unsigned byte from the input buffer, finds a decoder
	 * corresponding to that opcode, and returns an implementation of Instruction
	 * and reads any additional parameters.
	 * 
	 * @param buf buffer containing the JVM instructions
	 * @return implementation of Instruction corresponding to the next encoded
	 *         instruction
	 */
	public Instruction decode(ByteBuf buf) {
		int opcode = buf.readByte();
		Function<ByteBuf, Instruction> decoder = decoders.getOrDefault(opcode, getGenericDecoder(opcode));
		Instruction instruction = decoder.apply(buf);
		offset = ((DynamicByteBuf) buf).getPointer() + 1;
		return instruction;
	}

	/**
	 * Encodes target instruction and writes it to the input byte buffer.
	 * 
	 * @param buf         byte buffer to be written to
	 * @param instruction instruction to be encoded
	 */
	public void encode(ByteBuf buf, Instruction instruction) {
		buf.writeByte(instruction.getOpcode());
		instruction.write(buf);
	}

	private void initialize(int opcode, Class<? extends Instruction> clazz) {
		decoders.put(opcode, buf -> {
			try {
				Constructor<? extends Instruction> constructor = clazz.getDeclaredConstructor(int.class);
				constructor.setAccessible(true);
				Instruction instruction = constructor.newInstance(opcode);
				instruction.setWithOffset(offset);
				instruction.read(buf);
				return instruction;
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
		});
	}

	private Function<ByteBuf, Instruction> getGenericDecoder(int opcode) {
		return buf -> {
			Instruction instruction = new GenericInstruction(opcode);
			instruction.setWithOffset(offset);
			instruction.read(buf);
			return instruction;
		};
	}
}
