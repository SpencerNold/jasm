package me.spencernold.jasm.tools;

import me.spencernold.jasm.intermediary.code.instructions.Instruction;
import me.spencernold.jasm.intermediary.code.instructions.VarInstruction;
import me.spencernold.jasm.intermediary.pools.ConstPool;
import me.spencernold.jasm.utils.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static me.spencernold.jasm.Opcodes.*;

/**
 * DO NOT USE THIS CLASS, IT IS NOT DONE AND MAY NOT EVER BE FINISHED
 */
public class CodeTool {

    private static final Map<Integer, Integer> stackEffectMap = new HashMap<Integer, Integer>() {{
        put(AALOAD, -1);
        put(AASTORE, -3);
        put(ACONST_NULL, 1);
        put(ALOAD, 1);
        put(ALOAD_0, 1);
        put(ALOAD_1, 1);
        put(ALOAD_2, 1);
        put(ALOAD_3, 1);
        put(ARETURN, -1);
        put(ASTORE, -1);
        put(ASTORE_0, -1);
        put(ASTORE_1, -1);
        put(ASTORE_2, -1);
        put(ASTORE_3, -1);
        put(BALOAD, -1);
        put(BASTORE, -3);
        put(BIPUSH, 1);
        put(CALOAD, -1);
        put(CASTORE, -3);
        put(D2F, -1);
        put(D2I, -1);
        put(DADD, -2);
        put(DASTORE, -4);
        put(DCMPG, -3);
        put(DCMPL, -3);
        put(DCONST_0, 2);
        put(DCONST_1, 2);
        put(DDIV, -2);
        put(DLOAD, 2);
        put(DLOAD_0, 2);
        put(DLOAD_1, 2);
        put(DLOAD_2, 2);
        put(DLOAD_3, 2);
        put(DMUL, -2);
        put(DREM, -2);
        put(DRETURN, -2);
        put(DSTORE, -2);
        put(DSTORE_0, -2);
        put(DSTORE_1, -2);
        put(DSTORE_2, -2);
        put(DSTORE_3, -2);
        put(DSUB, -2);
        put(DUP, 1);
        put(DUP_X1, 1);
        put(DUP_X2, 1);
        put(DUP2, 2);
        put(DUP2_X1, 2);
        put(DUP2_X2, 2);
        put(F2D, 1);
        put(F2L, 1);
        put(FADD, -1);
        put(FALOAD, -1);
        put(FASTORE, -3);
        put(FCMPG, -1);
        put(FCMPL, -1);
        put(FCONST_0, 1);
        put(FCONST_1, 1);
        put(FCONST_2, 1);
        put(FDIV, -1);
        put(FLOAD, 1);
        put(FLOAD_0, 1);
        put(FLOAD_1, 1);
        put(FLOAD_2, 1);
        put(FLOAD_3, 1);
        put(FMUL, 1);
        put(FREM, -1);
        put(FRETURN, -1);
        put(FSTORE, -1);
        put(FSTORE_0, -1);
        put(FSTORE_1, -1);
        put(FSTORE_2, -1);
        put(FSTORE_3, -1);
        put(GETFIELD, Integer.MAX_VALUE);
        put(GETSTATIC, Integer.MAX_VALUE);
        put(I2D, 1);
        put(I2L, 1);
        put(IADD, -1);
        put(IALOAD, -1);
        put(IAND, -1);
        put(IASTORE, -3);
        put(ICONST_M1, 1);
        put(ICONST_0, 1);
        put(ICONST_1, 1);
        put(ICONST_2, 1);
        put(ICONST_3, 1);
        put(ICONST_4, 1);
        put(ICONST_5, 1);
        put(IDIV, -1);
        put(IF_ACMPEQ, -2);
        put(IF_ACMPNE, -2);
        put(IF_ICMPEQ, -2);
        put(IF_ICMPNE, -2);
        put(IF_ICMPLT, -2);
        put(IF_ICMPGT, -2);
        put(IF_ICMPGE, -2);
        put(IF_ICMPLE, -2);
        put(IFEQ, -1);
        put(IFNE, -1);
        put(IFGT, -1);
        put(IFLT, -1);
        put(IFGE, -1);
        put(IFLE, -1);
        put(IFNONNULL, -1);
        put(IFNULL, -1);
        put(ILOAD, 1);
        put(ILOAD_0, 1);
        put(ILOAD_1, 1);
        put(ILOAD_2, 1);
        put(ILOAD_3, 1);
        put(IMUL, -1);
        put(INVOKEDYNAMIC, Integer.MIN_VALUE);
        put(INVOKEINTERFACE, Integer.MIN_VALUE);
        put(INVOKESPECIAL, Integer.MIN_VALUE);
        put(INVOKESTATIC, Integer.MIN_VALUE);
        put(INVOKEVIRTUAL, Integer.MIN_VALUE);
        put(IOR, -1);
        put(IREM, -1);
        put(IRETURN, -1);
        put(ISHL, -1);
        put(ISHR, -1);
        put(ISTORE, -1);
        put(ISTORE_0, -1);
        put(ISTORE_1, -1);
        put(ISTORE_2, -1);
        put(ISTORE_3, -1);
        put(ISUB, -1);
        put(IUSHR, -1);
        put(IXOR, -1);
        put(JSR, 1);
        put(JSR_W, 1);
        put(L2F, -1);
        put(L2I, -1);
        put(LADD, -2);
        put(LAND, -2);
        put(LASTORE, 4);
        put(LCMP, -3);
        put(LCONST_0, 1);
        put(LCONST_1, 1);
        put(LDC, 1);
        put(LDC_W, 1);
        put(LDC2_W, 2);
        put(LDIV, -2);
        put(LLOAD, 2);
        put(LLOAD_0, 2);
        put(LLOAD_1, 2);
        put(LLOAD_2, 2);
        put(LLOAD_3, 2);
        put(LMUL, -2);
        put(LOOKUPSWITCH, -1);
        put(LOR, -2);
        put(LREM, -2);
        put(LRETURN, -2);
        put(LSHL, -2);
        put(LSHR, -2);
        put(LSTORE, -2);
        put(LSTORE_0, -2);
        put(LSTORE_1, -2);
        put(LSTORE_2, -2);
        put(LSTORE_3, -2);
        put(LSUB, -2);
        put(LUSHR, -2);
        put(LXOR, -2);
        put(MONITORENTER, -1);
        put(MONITOREXIT, -1);
        put(MULTIANEWARRAY, Integer.MIN_VALUE);
        put(NEW, 1);
        put(POP, -1);
        put(POP2, -2);
        put(PUTFIELD, Integer.MAX_VALUE);
        put(PUTSTATIC, Integer.MAX_VALUE);
        put(SALOAD, -1);
        put(SASTORE, -3);
        put(SIPUSH, 1);
        put(TABLESWITCH, -1);
    }};

    public static int getCalculatedStackSize(ConstPool constPool, Collection<Instruction> instructions) {
        int max = 0;
        int stack = 0;
        for (Instruction instruction : instructions) {
            stack += getStackEffect(constPool, instruction);
            max = Math.max(max, stack);
        }
        return max;
    }

    private static int getStackEffect(ConstPool constPool, Instruction instruction) {
        int effect = stackEffectMap.getOrDefault(instruction.getOpcode(), 0);
        if (effect == Integer.MIN_VALUE) {
            if (instruction.getOpcode() == MULTIANEWARRAY) {
                // TODO
            } else {
                // TODO INVOKE instructions
            }
        }
        if (effect == Integer.MAX_VALUE) {
            return 0; // TODO GET/PUT with type 1 vs type 2 computational types
        }
        return effect;
    }

    public static int getCalculatedLocalsSize(Collection<Instruction> instructions) {
        int size = 0;
        for (Instruction instruction : instructions) {
            Pair<Integer, Integer> val = getLocalIndexAndSize(instruction);
            size = Math.max(size, val.key + val.value);
        }
        return size;
    }

    private static Pair<Integer, Integer> getLocalIndexAndSize(Instruction instruction) {
        Pair<Integer, Integer> val = new Pair<>(0, 0);
        switch (instruction.getOpcode()) {
            case ASTORE_0:
            case ISTORE_0:
            case FSTORE_0:
                val.key = 0;
                val.value = 1;
                break;
            case ASTORE_1:
            case ISTORE_1:
            case FSTORE_1:
                val.key = 1;
                val.value = 1;
                break;
            case ASTORE_2:
            case ISTORE_2:
            case FSTORE_2:
                val.key = 2;
                val.value = 1;
                break;
            case ASTORE_3:
            case ISTORE_3:
            case FSTORE_3:
                val.key = 3;
                val.value = 1;
                break;
            case ASTORE:
            case ISTORE:
            case FSTORE:
                VarInstruction v1insn = (VarInstruction) instruction;
                val.key = v1insn.getValue();
                val.value = 1;
                break;
            case DSTORE_0:
            case LSTORE_0:
                val.key = 0;
                val.value = 2;
                break;
            case DSTORE_1:
            case LSTORE_1:
                val.key = 1;
                val.value = 2;
                break;
            case DSTORE_2:
            case LSTORE_2:
                val.key = 2;
                val.value = 2;
                break;
            case DSTORE_3:
            case LSTORE_3:
                val.key = 3;
                val.value = 2;
                break;
            case DSTORE:
            case LSTORE:
                VarInstruction v2insn = (VarInstruction) instruction;
                val.key = v2insn.getValue();
                val.value = 2;
                break;
        }
        return val;
    }
}
