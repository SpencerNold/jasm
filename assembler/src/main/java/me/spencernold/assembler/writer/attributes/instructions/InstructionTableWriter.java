package me.spencernold.assembler.writer.attributes.instructions;

import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.assembler.writer.attributes.instructions.writables.*;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.intermediary.JWritable;
import me.spencernold.jasm.intermediary.code.Code;
import me.spencernold.jasm.intermediary.code.instructions.*;
import me.spencernold.jasm.intermediary.pools.ConstPool;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class InstructionTableWriter implements JWritable<StringWriter> {

    private static InstructionTableWriter instance;

    public final HashMap<Integer, InstructionWritable> writables = new HashMap<>();

    private final ConstPool constPool;
    private final Code code;

    public InstructionTableWriter(ConstPool constPool, Code code) {
        this.constPool = constPool;
        this.code = code;
        // Generic instruction writables
        writables.put(Opcodes.AALOAD, new GenericInstructionWritable("aaload"));
        writables.put(Opcodes.AASTORE, new GenericInstructionWritable("aastore"));
        writables.put(Opcodes.ACONST_NULL, new GenericInstructionWritable("aconst_null"));
        writables.put(Opcodes.ALOAD_0, new GenericInstructionWritable("aload_0"));
        writables.put(Opcodes.ALOAD_1, new GenericInstructionWritable("aload_1"));
        writables.put(Opcodes.ALOAD_2, new GenericInstructionWritable("aload_2"));
        writables.put(Opcodes.ALOAD_3, new GenericInstructionWritable("aload_3"));
        writables.put(Opcodes.ARETURN, new GenericInstructionWritable("areturn"));
        writables.put(Opcodes.ARRAYLENGTH, new GenericInstructionWritable("arraylength"));
        writables.put(Opcodes.ASTORE_0, new GenericInstructionWritable("astore_0"));
        writables.put(Opcodes.ASTORE_1, new GenericInstructionWritable("astore_1"));
        writables.put(Opcodes.ASTORE_2, new GenericInstructionWritable("astore_2"));
        writables.put(Opcodes.ASTORE_3, new GenericInstructionWritable("astore_3"));
        writables.put(Opcodes.ATHROW, new GenericInstructionWritable("athrow"));
        writables.put(Opcodes.BALOAD, new GenericInstructionWritable("baload"));
        writables.put(Opcodes.BASTORE, new GenericInstructionWritable("bastore"));
        writables.put(Opcodes.CALOAD, new GenericInstructionWritable("caload"));
        writables.put(Opcodes.CASTORE, new GenericInstructionWritable("castore"));
        writables.put(Opcodes.D2F, new GenericInstructionWritable("d2f"));
        writables.put(Opcodes.D2I, new GenericInstructionWritable("d2i"));
        writables.put(Opcodes.D2L, new GenericInstructionWritable("d2l"));
        writables.put(Opcodes.DADD, new GenericInstructionWritable("dadd"));
        writables.put(Opcodes.DALOAD, new GenericInstructionWritable("daload"));
        writables.put(Opcodes.DASTORE, new GenericInstructionWritable("dastore"));
        writables.put(Opcodes.DCMPG, new GenericInstructionWritable("dcmpg"));
        writables.put(Opcodes.DCMPL, new GenericInstructionWritable("dcmpl"));
        writables.put(Opcodes.DCONST_0, new GenericInstructionWritable("dconst_0"));
        writables.put(Opcodes.DCONST_1, new GenericInstructionWritable("dconst_1"));
        writables.put(Opcodes.DDIV, new GenericInstructionWritable("ddiv"));
        writables.put(Opcodes.DLOAD_0, new GenericInstructionWritable("dload_0"));
        writables.put(Opcodes.DLOAD_1, new GenericInstructionWritable("dload_1"));
        writables.put(Opcodes.DLOAD_2, new GenericInstructionWritable("dload_2"));
        writables.put(Opcodes.DLOAD_3, new GenericInstructionWritable("dload_3"));
        writables.put(Opcodes.DMUL, new GenericInstructionWritable("dmul"));
        writables.put(Opcodes.DNEG, new GenericInstructionWritable("dneg"));
        writables.put(Opcodes.DREM, new GenericInstructionWritable("drem"));
        writables.put(Opcodes.DRETURN, new GenericInstructionWritable("dreturn"));
        writables.put(Opcodes.DSTORE_0, new GenericInstructionWritable("dstore_0"));
        writables.put(Opcodes.DSTORE_1, new GenericInstructionWritable("dstore_1"));
        writables.put(Opcodes.DSTORE_2, new GenericInstructionWritable("dstore_2"));
        writables.put(Opcodes.DSTORE_3, new GenericInstructionWritable("dstore_3"));
        writables.put(Opcodes.DSUB, new GenericInstructionWritable("dsub"));
        writables.put(Opcodes.DUP, new GenericInstructionWritable("dup"));
        writables.put(Opcodes.DUP_X1, new GenericInstructionWritable("dup_x1"));
        writables.put(Opcodes.DUP_X2, new GenericInstructionWritable("dup_x2"));
        writables.put(Opcodes.DUP2, new GenericInstructionWritable("dup2"));
        writables.put(Opcodes.DUP2_X1, new GenericInstructionWritable("dup2_x1"));
        writables.put(Opcodes.DUP2_X2, new GenericInstructionWritable("dup2_x2"));
        writables.put(Opcodes.F2D, new GenericInstructionWritable("f2d"));
        writables.put(Opcodes.F2I, new GenericInstructionWritable("f2i"));
        writables.put(Opcodes.F2L, new GenericInstructionWritable("f2l"));
        writables.put(Opcodes.FADD, new GenericInstructionWritable("fadd"));
        writables.put(Opcodes.FALOAD, new GenericInstructionWritable("faload"));
        writables.put(Opcodes.FASTORE, new GenericInstructionWritable("fastore"));
        writables.put(Opcodes.FCMPG, new GenericInstructionWritable("fcmpg"));
        writables.put(Opcodes.FCMPL, new GenericInstructionWritable("fcmpl"));
        writables.put(Opcodes.FCONST_0, new GenericInstructionWritable("fconst_0"));
        writables.put(Opcodes.FCONST_1, new GenericInstructionWritable("fconst_1"));
        writables.put(Opcodes.FCONST_2, new GenericInstructionWritable("fconst_2"));
        writables.put(Opcodes.FDIV, new GenericInstructionWritable("fdiv"));
        writables.put(Opcodes.FLOAD_0, new GenericInstructionWritable("fload_0"));
        writables.put(Opcodes.FLOAD_1, new GenericInstructionWritable("fload_1"));
        writables.put(Opcodes.FLOAD_2, new GenericInstructionWritable("fload_2"));
        writables.put(Opcodes.FLOAD_3, new GenericInstructionWritable("float_3"));
        writables.put(Opcodes.FMUL, new GenericInstructionWritable("fmul"));
        writables.put(Opcodes.FNEG, new GenericInstructionWritable("fneg"));
        writables.put(Opcodes.FREM, new GenericInstructionWritable("frem"));
        writables.put(Opcodes.FRETURN, new GenericInstructionWritable("freturn"));
        writables.put(Opcodes.FSTORE_0, new GenericInstructionWritable("fstore_0"));
        writables.put(Opcodes.FSTORE_1, new GenericInstructionWritable("fstore_1"));
        writables.put(Opcodes.FSTORE_2, new GenericInstructionWritable("fstore_2"));
        writables.put(Opcodes.FSTORE_3, new GenericInstructionWritable("fstore_3"));
        writables.put(Opcodes.FSUB, new GenericInstructionWritable("fsub"));
        writables.put(Opcodes.I2B, new GenericInstructionWritable("i2b"));
        writables.put(Opcodes.I2C, new GenericInstructionWritable("i2c"));
        writables.put(Opcodes.I2S, new GenericInstructionWritable("i2s"));
        writables.put(Opcodes.I2L, new GenericInstructionWritable("i2l"));
        writables.put(Opcodes.I2F, new GenericInstructionWritable("i2f"));
        writables.put(Opcodes.I2D, new GenericInstructionWritable("i2d"));
        writables.put(Opcodes.IADD, new GenericInstructionWritable("iadd"));
        writables.put(Opcodes.IALOAD, new GenericInstructionWritable("iaload"));
        writables.put(Opcodes.IAND, new GenericInstructionWritable("iand"));
        writables.put(Opcodes.IASTORE, new GenericInstructionWritable("iastore"));
        writables.put(Opcodes.ICONST_0, new GenericInstructionWritable("iconst_0"));
        writables.put(Opcodes.ICONST_1, new GenericInstructionWritable("iconst_1"));
        writables.put(Opcodes.ICONST_2, new GenericInstructionWritable("iconst_2"));
        writables.put(Opcodes.ICONST_3, new GenericInstructionWritable("iconst_3"));
        writables.put(Opcodes.ICONST_4, new GenericInstructionWritable("iconst_4"));
        writables.put(Opcodes.ICONST_5, new GenericInstructionWritable("iconst_5"));
        writables.put(Opcodes.IDIV, new GenericInstructionWritable("idiv"));
        writables.put(Opcodes.ILOAD_0, new GenericInstructionWritable("iload_0"));
        writables.put(Opcodes.ILOAD_1, new GenericInstructionWritable("iload_1"));
        writables.put(Opcodes.ILOAD_2, new GenericInstructionWritable("iload_2"));
        writables.put(Opcodes.ILOAD_3, new GenericInstructionWritable("iload_3"));
        writables.put(Opcodes.IMUL, new GenericInstructionWritable("imul"));
        writables.put(Opcodes.INEG, new GenericInstructionWritable("ineg"));
        writables.put(Opcodes.IOR, new GenericInstructionWritable("ior"));
        writables.put(Opcodes.IREM, new GenericInstructionWritable("irem"));
        writables.put(Opcodes.ISHL, new GenericInstructionWritable("ishl"));
        writables.put(Opcodes.ISHR, new GenericInstructionWritable("ishr"));
        writables.put(Opcodes.ISTORE_0, new GenericInstructionWritable("istore_0"));
        writables.put(Opcodes.ISTORE_1, new GenericInstructionWritable("istore_1"));
        writables.put(Opcodes.ISTORE_2, new GenericInstructionWritable("istore_2"));
        writables.put(Opcodes.ISTORE_3, new GenericInstructionWritable("istore_3"));
        writables.put(Opcodes.ISUB, new GenericInstructionWritable("isub"));
        writables.put(Opcodes.IUSHR, new GenericInstructionWritable("iushr"));
        writables.put(Opcodes.IXOR, new GenericInstructionWritable("ixor"));
        writables.put(Opcodes.L2D, new GenericInstructionWritable("l2d"));
        writables.put(Opcodes.L2F, new GenericInstructionWritable("l2f"));
        writables.put(Opcodes.L2I, new GenericInstructionWritable("l2i"));
        writables.put(Opcodes.LADD, new GenericInstructionWritable("ladd"));
        writables.put(Opcodes.LALOAD, new GenericInstructionWritable("laload"));
        writables.put(Opcodes.LAND, new GenericInstructionWritable("land"));
        writables.put(Opcodes.LASTORE, new GenericInstructionWritable("lastore"));
        writables.put(Opcodes.LCMP, new GenericInstructionWritable("lcmp"));
        writables.put(Opcodes.LCONST_0, new GenericInstructionWritable("lconst_0"));
        writables.put(Opcodes.LCONST_1, new GenericInstructionWritable("lconst_1"));
        writables.put(Opcodes.LDIV, new GenericInstructionWritable("ldiv"));
        writables.put(Opcodes.LLOAD_0, new GenericInstructionWritable("lload_0"));
        writables.put(Opcodes.LLOAD_1, new GenericInstructionWritable("lload_1"));
        writables.put(Opcodes.LLOAD_2, new GenericInstructionWritable("lload_2"));
        writables.put(Opcodes.LLOAD_3, new GenericInstructionWritable("lload_3"));
        writables.put(Opcodes.LMUL, new GenericInstructionWritable("lmul"));
        writables.put(Opcodes.LOR, new GenericInstructionWritable("lor"));
        writables.put(Opcodes.LREM, new GenericInstructionWritable("lrem"));
        writables.put(Opcodes.LRETURN, new GenericInstructionWritable("lreturn"));
        writables.put(Opcodes.LSHL, new GenericInstructionWritable("lshl"));
        writables.put(Opcodes.LSHR, new GenericInstructionWritable("lshr"));
        writables.put(Opcodes.LSTORE_0, new GenericInstructionWritable("lstore_0"));
        writables.put(Opcodes.LSTORE_1, new GenericInstructionWritable("lstore_1"));
        writables.put(Opcodes.LSTORE_2, new GenericInstructionWritable("lstore_2"));
        writables.put(Opcodes.LSTORE_3, new GenericInstructionWritable("lstore_3"));
        writables.put(Opcodes.LSUB, new GenericInstructionWritable("lsub"));
        writables.put(Opcodes.LUSHR, new GenericInstructionWritable("lushr"));
        writables.put(Opcodes.LXOR, new GenericInstructionWritable("lxor"));
        writables.put(Opcodes.MONITORENTER, new GenericInstructionWritable("monitorenter"));
        writables.put(Opcodes.MONITOREXIT, new GenericInstructionWritable("monitorexit"));
        writables.put(Opcodes.NOP, new GenericInstructionWritable("nop"));
        writables.put(Opcodes.POP, new GenericInstructionWritable("pop"));
        writables.put(Opcodes.POP2, new GenericInstructionWritable("pop2"));
        writables.put(Opcodes.RETURN, new GenericInstructionWritable("return"));
        writables.put(Opcodes.SALOAD, new GenericInstructionWritable("saload"));
        writables.put(Opcodes.SASTORE, new GenericInstructionWritable("sastore"));
        writables.put(Opcodes.SWAP, new GenericInstructionWritable("swap"));
        // Variable instruction writables
        writables.put(Opcodes.ALOAD, new VarInstructionWritable("aload"));
        writables.put(Opcodes.ASTORE, new VarInstructionWritable("astore"));
        writables.put(Opcodes.DLOAD, new VarInstructionWritable("dload"));
        writables.put(Opcodes.DSTORE, new VarInstructionWritable("dstore"));
        writables.put(Opcodes.FLOAD, new VarInstructionWritable("fload"));
        writables.put(Opcodes.FSTORE, new VarInstructionWritable("fstore"));
        writables.put(Opcodes.ILOAD, new VarInstructionWritable("iload"));
        writables.put(Opcodes.ISTORE, new VarInstructionWritable("istore"));
        writables.put(Opcodes.LLOAD, new VarInstructionWritable("lload"));
        writables.put(Opcodes.LSTORE, new VarInstructionWritable("lstore"));
        writables.put(Opcodes.NEWARRAY, new VarInstructionWritable("newarray"));
        writables.put(Opcodes.RET, new SingleReferenceInstructionWritable("ret"));
        writables.put(Opcodes.LDC, new SingleReferenceInstructionWritable("ldc"));
        // Push instruction writables
        writables.put(Opcodes.BIPUSH, new PushInstructionWritable("bipush"));
        writables.put(Opcodes.SIPUSH, new PushInstructionWritable("sipush"));
        // Reference instruction writables
        writables.put(Opcodes.ANEWARRAY, new ReferenceInstructionWritable("anewarray"));
        writables.put(Opcodes.CHECKCAST, new ReferenceInstructionWritable("checkcast"));
        writables.put(Opcodes.GETFIELD, new ReferenceInstructionWritable("getfield"));
        writables.put(Opcodes.GETSTATIC, new ReferenceInstructionWritable("getstatic"));
        writables.put(Opcodes.INSTANCEOF, new ReferenceInstructionWritable("instanceof"));
        writables.put(Opcodes.INVOKESPECIAL, new ReferenceInstructionWritable("invokespecial"));
        writables.put(Opcodes.INVOKESTATIC, new ReferenceInstructionWritable("invokestatic"));
        writables.put(Opcodes.INVOKEVIRTUAL, new ReferenceInstructionWritable("invokevirtual"));
        writables.put(Opcodes.LDC_W, new ReferenceInstructionWritable("ldc_w"));
        writables.put(Opcodes.LDC2_W, new ReferenceInstructionWritable("ldc2_w"));
        writables.put(Opcodes.NEW, new ReferenceInstructionWritable("new"));
        writables.put(Opcodes.PUTFIELD, new ReferenceInstructionWritable("putfield"));
        writables.put(Opcodes.PUTSTATIC, new ReferenceInstructionWritable("putstatic"));
        // Wide Reference instructions
        writables.put(Opcodes.INVOKEDYNAMIC, new WideVarInstructionWritable("invokedynamic"));
        writables.put(Opcodes.INVOKEINTERFACE, new WideVarInstructionWritable("invokeinterface"));
        // Control Flow instructions
        writables.put(Opcodes.GOTO, new ControlFlowInstructionWritable("goto"));
        writables.put(Opcodes.IF_ACMPEQ, new ControlFlowInstructionWritable("if_acmpeq"));
        writables.put(Opcodes.IF_ACMPNE, new ControlFlowInstructionWritable("if_acmpne"));
        writables.put(Opcodes.IF_ICMPEQ, new ControlFlowInstructionWritable("if_icmpeq"));
        writables.put(Opcodes.IF_ICMPGE, new ControlFlowInstructionWritable("if_icmpge"));
        writables.put(Opcodes.IF_ICMPGT, new ControlFlowInstructionWritable("if_icmpgt"));
        writables.put(Opcodes.IF_ICMPLE, new ControlFlowInstructionWritable("if_icmple"));
        writables.put(Opcodes.IF_ICMPLT, new ControlFlowInstructionWritable("if_icmplt"));
        writables.put(Opcodes.IF_ICMPNE, new ControlFlowInstructionWritable("if_icmpne"));
        writables.put(Opcodes.IFEQ, new ControlFlowInstructionWritable("ifeq"));
        writables.put(Opcodes.IFGE, new ControlFlowInstructionWritable("ifge"));
        writables.put(Opcodes.IFGT, new ControlFlowInstructionWritable("ifgt"));
        writables.put(Opcodes.IFLE, new ControlFlowInstructionWritable("ifle"));
        writables.put(Opcodes.IFLT, new ControlFlowInstructionWritable("iflt"));
        writables.put(Opcodes.IFNE, new ControlFlowInstructionWritable("ifne"));
        writables.put(Opcodes.IFNONNULL, new ControlFlowInstructionWritable("ifnonnull"));
        writables.put(Opcodes.IFNULL, new ControlFlowInstructionWritable("ifnull"));
        writables.put(Opcodes.JSR, new ControlFlowInstructionWritable("jsr"));
        // Wide Control Flow instructions
        writables.put(Opcodes.GOTO_W, new WideControlFlowInstructionWritable("goto_w"));
        writables.put(Opcodes.JSR_W, new WideControlFlowInstructionWritable("jsr_w"));
        // other special instructions
        writables.put(Opcodes.IINC, new IncrementInstructionWritable("iinc"));
        writables.put(Opcodes.NEWARRAY, new NewArrayInstructionWritable("newarray"));
        writables.put(Opcodes.MULTIANEWARRAY, new NewMultiArrayInstructionWritable("multianewarray"));
        writables.put(Opcodes.TABLESWITCH, new TableSwitchInstructionWritable("tableswitch"));
        writables.put(Opcodes.LOOKUPSWITCH, new LookupSwitchInstructionWritable("lookupswitch"));
        writables.put(Opcodes.WIDE, new WideInstructionWritable(this));
    }

    @Override
    public void write(StringWriter writer) {
        writer.println("instructions {");
        writer.incrementTabCount();
        for (Instruction instruction : code) {
            InstructionWritable writable = writables.get(instruction.getOpcode());
            writable.write(writer, instruction);
        }
        writer.decrementTabCount();
        writer.println("}");
    }
}
