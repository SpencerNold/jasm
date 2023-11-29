package me.spencernold.assembler.parser.attributes.code;

import java.util.HashMap;
import java.util.LinkedList;

import me.spencernold.assembler.Main;
import me.spencernold.assembler.ObjectWriter;
import me.spencernold.assembler.parser.ParserWriter;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.intermediary.attributes.CodeAttribute;
import me.spencernold.jasm.intermediary.code.instructions.ControlFlowInstruction;
import me.spencernold.jasm.intermediary.code.instructions.IncrementInstruction;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.LookupSwitchInstruction;
import me.spencernold.jasm.intermediary.code.instructions.NewArrayInstruction;
import me.spencernold.jasm.intermediary.code.instructions.NewMultiArrayInstruction;
import me.spencernold.jasm.intermediary.code.instructions.PushInstruction;
import me.spencernold.jasm.intermediary.code.instructions.ReferenceInstruction;
import me.spencernold.jasm.intermediary.code.instructions.TableSwitchInstruction;
import me.spencernold.jasm.intermediary.code.instructions.VarInstruction;
import me.spencernold.jasm.intermediary.code.instructions.WideControlFlowInstruction;
import me.spencernold.jasm.intermediary.code.instructions.WideIncrementInstruction;
import me.spencernold.jasm.intermediary.code.instructions.WideInstruction;
import me.spencernold.jasm.intermediary.code.instructions.WideVarInstruction;

public class InstructionWriter implements ParserWriter<CodeAttribute> {

	@SuppressWarnings("deprecation")
	private final HashMap<Integer, String> opcodeToNameTranslator = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 4089233822792472592L;
		{
			put(Opcodes.AALOAD, "aaload");
			put(Opcodes.AASTORE, "aastore");
			put(Opcodes.ACONST_NULL, "aconst_null");
			put(Opcodes.ALOAD, "aload");
			put(Opcodes.ALOAD_0, "aload_0");
			put(Opcodes.ALOAD_1, "aload_1");
			put(Opcodes.ALOAD_2, "aload_2");
			put(Opcodes.ALOAD_3, "aload_3");
			put(Opcodes.ANEWARRAY, "anewarray");
			put(Opcodes.ARETURN, "areturn");
			put(Opcodes.ARRAYLENGTH, "arraylength");
			put(Opcodes.ASTORE, "astore");
			put(Opcodes.ASTORE_0, "astore_0");
			put(Opcodes.ASTORE_1, "astore_1");
			put(Opcodes.ASTORE_2, "astore_2");
			put(Opcodes.ASTORE_3, "astore_3");
			put(Opcodes.ATHROW, "athrow");
			put(Opcodes.BALOAD, "baload");
			put(Opcodes.BASTORE, "bastore");
			put(Opcodes.BIPUSH, "bipush");
			put(Opcodes.BREAKPOINT, "breakpoint");
			put(Opcodes.CALOAD, "caload");
			put(Opcodes.CASTORE, "castore");
			put(Opcodes.CHECKCAST, "checkcast");
			put(Opcodes.D2F, "d2f");
			put(Opcodes.D2I, "d2i");
			put(Opcodes.D2L, "d2l");
			put(Opcodes.DADD, "dadd");
			put(Opcodes.DALOAD, "daload");
			put(Opcodes.DASTORE, "dastore");
			put(Opcodes.DCMPG, "dcmpg");
			put(Opcodes.DCMPL, "dcmpl");
			put(Opcodes.DCONST_0, "dconst_0");
			put(Opcodes.DCONST_1, "dconst_1");
			put(Opcodes.DDIV, "ddiv");
			put(Opcodes.DLOAD, "dload");
			put(Opcodes.DLOAD_0, "dload_0");
			put(Opcodes.DLOAD_1, "dload_1");
			put(Opcodes.DLOAD_2, "dload_2");
			put(Opcodes.DLOAD_3, "dload_3");
			put(Opcodes.DMUL, "dmul");
			put(Opcodes.DNEG, "dneg");
			put(Opcodes.DREM, "drem");
			put(Opcodes.DRETURN, "dreturn");
			put(Opcodes.DSTORE, "dstore");
			put(Opcodes.DSTORE_0, "dstore_0");
			put(Opcodes.DSTORE_1, "dstore_1");
			put(Opcodes.DSTORE_2, "dstore_2");
			put(Opcodes.DSTORE_3, "dstore_3");
			put(Opcodes.DSUB, "dsub");
			put(Opcodes.DUP, "dup");
			put(Opcodes.DUP_X1, "dup_x1");
			put(Opcodes.DUP_X2, "dup_x2");
			put(Opcodes.DUP2, "dup2");
			put(Opcodes.DUP2_X1, "dup2_x1");
			put(Opcodes.DUP2_X2, "dup2_x2");
			put(Opcodes.F2D, "f2d");
			put(Opcodes.F2I, "f2i");
			put(Opcodes.F2L, "f2l");
			put(Opcodes.FADD, "fadd");
			put(Opcodes.FALOAD, "faload");
			put(Opcodes.FASTORE, "fastore");
			put(Opcodes.FCMPG, "fcmpg");
			put(Opcodes.FCMPL, "fcmpl");
			put(Opcodes.FCONST_0, "fconst_0");
			put(Opcodes.FCONST_1, "fconst_1");
			put(Opcodes.FCONST_2, "fconst_2");
			put(Opcodes.FDIV, "fdiv");
			put(Opcodes.FLOAD, "fload");
			put(Opcodes.FLOAD_0, "fload_0");
			put(Opcodes.FLOAD_1, "fload_1");
			put(Opcodes.FLOAD_2, "fload_2");
			put(Opcodes.FLOAD_3, "fload_3");
			put(Opcodes.FMUL, "fmul");
			put(Opcodes.FNEG, "fneg");
			put(Opcodes.FREM, "frem");
			put(Opcodes.FRETURN, "freturn");
			put(Opcodes.FSTORE, "fstore");
			put(Opcodes.FSTORE_0, "fstore_0");
			put(Opcodes.FSTORE_1, "fstore_1");
			put(Opcodes.FSTORE_2, "fstore_2");
			put(Opcodes.FSTORE_3, "fstore_3");
			put(Opcodes.FSUB, "fsub");
			put(Opcodes.GETFIELD, "getfield");
			put(Opcodes.GETSTATIC, "getstatic");
			put(Opcodes.GOTO, "goto");
			put(Opcodes.GOTO_W, "goto_w");
			put(Opcodes.I2B, "i2b");
			put(Opcodes.I2C, "i2c");
			put(Opcodes.I2D, "i2d");
			put(Opcodes.I2F, "i2f");
			put(Opcodes.I2L, "i2l");
			put(Opcodes.I2S, "i2s");
			put(Opcodes.IADD, "iadd");
			put(Opcodes.IALOAD, "iaload");
			put(Opcodes.IAND, "iand");
			put(Opcodes.IASTORE, "iastore");
			put(Opcodes.ICONST_M1, "iconst_m1");
			put(Opcodes.ICONST_0, "iconst_0");
			put(Opcodes.ICONST_1, "iconst_1");
			put(Opcodes.ICONST_2, "iconst_2");
			put(Opcodes.ICONST_3, "iconst_3");
			put(Opcodes.ICONST_4, "iconst_4");
			put(Opcodes.ICONST_5, "iconst_5");
			put(Opcodes.IDIV, "idiv");
			put(Opcodes.IF_ACMPEQ, "if_acmpeq");
			put(Opcodes.IF_ACMPNE, "if_acmpne");
			put(Opcodes.IF_ICMPEQ, "if_icmpeq");
			put(Opcodes.IF_ICMPGE, "if_icmpge");
			put(Opcodes.IF_ICMPGT, "if_icmpgt");
			put(Opcodes.IF_ICMPLE, "if_icmple");
			put(Opcodes.IF_ICMPLT, "if_icmplt");
			put(Opcodes.IF_ICMPNE, "if_icmpne");
			put(Opcodes.IFEQ, "ifeq");
			put(Opcodes.IFGE, "ifge");
			put(Opcodes.IFGT, "ifgt");
			put(Opcodes.IFLE, "ifle");
			put(Opcodes.IFLT, "iflt");
			put(Opcodes.IFNE, "ifne");
			put(Opcodes.IFNONNULL, "ifnonnull");
			put(Opcodes.IFNULL, "ifnull");
			put(Opcodes.IINC, "iinc");
			put(Opcodes.ILOAD, "iload");
			put(Opcodes.ILOAD_0, "iload_0");
			put(Opcodes.ILOAD_1, "iload_1");
			put(Opcodes.ILOAD_2, "iload_2");
			put(Opcodes.ILOAD_3, "iload_3");
			put(Opcodes.IMPDEP1, "impdep1");
			put(Opcodes.IMPDEP2, "impdep2");
			put(Opcodes.IMUL, "imul");
			put(Opcodes.INEG, "ineg");
			put(Opcodes.INSTANCEOF, "instanceof");
			put(Opcodes.INVOKEDYNAMIC, "invokedynamic");
			put(Opcodes.INVOKEINTERFACE, "invokeinterface");
			put(Opcodes.INVOKESPECIAL, "invokespecial");
			put(Opcodes.INVOKESTATIC, "invokestatic");
			put(Opcodes.INVOKEVIRTUAL, "invokevirtual");
			put(Opcodes.IOR, "ior");
			put(Opcodes.IREM, "irem");
			put(Opcodes.IRETURN, "ireturn");
			put(Opcodes.ISHL, "ishl");
			put(Opcodes.ISHR, "ishr");
			put(Opcodes.ISTORE, "istore");
			put(Opcodes.ISTORE_0, "istore_0");
			put(Opcodes.ISTORE_1, "istore_1");
			put(Opcodes.ISTORE_2, "istore_2");
			put(Opcodes.ISTORE_3, "istore_3");
			put(Opcodes.ISUB, "isub");
			put(Opcodes.IUSHR, "iushr");
			put(Opcodes.IXOR, "ixor");
			put(Opcodes.JSR, "jsr");
			put(Opcodes.JSR_W, "jsr_w");
			put(Opcodes.L2D, "l2d");
			put(Opcodes.L2F, "l2f");
			put(Opcodes.L2I, "l2i");
			put(Opcodes.LADD, "ladd");
			put(Opcodes.LALOAD, "laload");
			put(Opcodes.LAND, "land");
			put(Opcodes.LASTORE, "lastore");
			put(Opcodes.LCMP, "lcmp");
			put(Opcodes.LCONST_0, "lconst_0");
			put(Opcodes.LCONST_1, "lconst_1");
			put(Opcodes.LDC, "ldc");
			put(Opcodes.LDC_W, "ldc_w");
			put(Opcodes.LDC2_W, "ldc2_w");
			put(Opcodes.LDIV, "ldiv");
			put(Opcodes.LLOAD, "lload");
			put(Opcodes.LLOAD_0, "lload_0");
			put(Opcodes.LLOAD_1, "lload_1");
			put(Opcodes.LLOAD_2, "lload_2");
			put(Opcodes.LLOAD_3, "lload_3");
			put(Opcodes.LMUL, "lmul");
			put(Opcodes.LNEG, "lneg");
			put(Opcodes.LOOKUPSWITCH, "lookupswitch");
			put(Opcodes.LOR, "lor");
			put(Opcodes.LREM, "lrem");
			put(Opcodes.LRETURN, "lreturn");
			put(Opcodes.LSHL, "lshl");
			put(Opcodes.LSHR, "lshr");
			put(Opcodes.LSTORE, "lstore");
			put(Opcodes.LSTORE_0, "lstore_0");
			put(Opcodes.LSTORE_1, "lstore_1");
			put(Opcodes.LSTORE_2, "lstore_2");
			put(Opcodes.LSTORE_3, "lstore_3");
			put(Opcodes.LSUB, "lsub");
			put(Opcodes.LUSHR, "lushr");
			put(Opcodes.LXOR, "lxor");
			put(Opcodes.MONITORENTER, "monitorenter");
			put(Opcodes.MONITOREXIT, "monitorexit");
			put(Opcodes.MULTIANEWARRAY, "multianewarray");
			put(Opcodes.NEW, "new");
			put(Opcodes.NEWARRAY, "newarray");
			put(Opcodes.NOP, "nop");
			put(Opcodes.POP, "pop");
			put(Opcodes.POP2, "pop2");
			put(Opcodes.PUTFIELD, "putfield");
			put(Opcodes.PUTSTATIC, "putstatic");
			put(Opcodes.RET, "ret");
			put(Opcodes.RETURN, "return");
			put(Opcodes.SALOAD, "saload");
			put(Opcodes.SASTORE, "sastore");
			put(Opcodes.SIPUSH, "sipush");
			put(Opcodes.SWAP, "swap");
			put(Opcodes.TABLESWITCH, "tableswitch");
			put(Opcodes.WIDE, "wide");


		}
	};

	private LinkedList<Instruction> instructions;

	@Override
	public void read(CodeAttribute attribute) {
		instructions = attribute.getInstructions().getCode().getInstructions();
	}

	@Override
	public void write(ObjectWriter writer) {
		for (Instruction instruction : instructions) {
			writer.printf("%s", opcodeToNameTranslator.getOrDefault(instruction.getOpcode(), "nop"));
			if (instruction instanceof ControlFlowInstruction)
				writeInstruction(writer, (ControlFlowInstruction) instruction);
			else if (instruction instanceof IncrementInstruction)
				writeInstruction(writer, (IncrementInstruction) instruction);
			else if (instruction instanceof LookupSwitchInstruction)
				writeInstruction(writer, (LookupSwitchInstruction) instruction);
			else if (instruction instanceof NewArrayInstruction)
				writeInstruction(writer, (NewArrayInstruction) instruction);
			else if (instruction instanceof NewMultiArrayInstruction)
				writeInstruction(writer, (NewMultiArrayInstruction) instruction);
			else if (instruction instanceof PushInstruction)
				writeInstruction(writer, (PushInstruction) instruction);
			else if (instruction instanceof ReferenceInstruction)
				writeInstruction(writer, (ReferenceInstruction) instruction);
			else if (instruction instanceof TableSwitchInstruction)
				writeInstruction(writer, (TableSwitchInstruction) instruction);
			else if (instruction instanceof VarInstruction)
				writeInstruction(writer, (VarInstruction) instruction);
			else if (instruction instanceof WideControlFlowInstruction)
				writeInstruction(writer, (WideControlFlowInstruction) instruction);
			else if (instruction instanceof WideIncrementInstruction)
				writeInstruction(writer, (WideIncrementInstruction) instruction);
			else if (instruction instanceof WideInstruction)
				writeInstruction(writer, (WideInstruction) instruction);
			else if (instruction instanceof WideVarInstruction)
				writeInstruction(writer, (WideVarInstruction) instruction);
			writer.printlnRaw("");
		}
	}

	private void writeInstruction(ObjectWriter writer, ControlFlowInstruction instruction) {
		writer.printf(" %d", instruction.getBranch());
	}

	private void writeInstruction(ObjectWriter writer, IncrementInstruction instruction) {
		writer.printf(" #%d, %d", instruction.getIndex(), instruction.getConstant());
	}

	private void writeInstruction(ObjectWriter writer, LookupSwitchInstruction instruction) {
		
	}

	private void writeInstruction(ObjectWriter writer, NewArrayInstruction instruction) {

	}

	private void writeInstruction(ObjectWriter writer, NewMultiArrayInstruction instruction) {

	}

	private void writeInstruction(ObjectWriter writer, PushInstruction instruction) {

	}

	private void writeInstruction(ObjectWriter writer, ReferenceInstruction instruction) {

	}

	private void writeInstruction(ObjectWriter writer, TableSwitchInstruction instruction) {
		
	}

	private void writeInstruction(ObjectWriter writer, VarInstruction instruction) {

	}
	
	private void writeInstruction(ObjectWriter writer, WideControlFlowInstruction instruction) {

	}

	private void writeInstruction(ObjectWriter writer, WideInstruction instruction) {
		Instruction child = instruction.getChild();
		if (child instanceof WideIncrementInstruction)
			writeInstruction(writer, (WideIncrementInstruction) child);
		else if (child instanceof WideVarInstruction)
			writeInstruction(writer, (WideVarInstruction) child);
		else
			Main.error("%s is not an instance of a wide instruction", child.getClass().getName());
	}
	
	private void writeInstruction(ObjectWriter writer, WideIncrementInstruction instruction) {

	}

	private void writeInstruction(ObjectWriter writer, WideVarInstruction instruction) {
		writer.printf("_w %d", instruction.getValue());
	}
}
