package me.spencernold.assembler.writer;

import me.spencernold.jasm.intermediary.JWritable;
import me.spencernold.jasm.intermediary.constants.*;
import me.spencernold.jasm.intermediary.pools.ConstPool;

import static me.spencernold.jasm.Opcodes.*;

public class ConstPoolWriter implements JWritable<StringWriter> {

    private final ConstPool constPool;

    public ConstPoolWriter(ConstPool constPool) {
        this.constPool = constPool;
    }

    @Override
    public void write(StringWriter writer) {
        writer.println("constants {");
        writer.incrementTabCount();
        for (Constant constant : constPool)
            writer.printf("#%d %s = %s\n", constant.getConstPoolIndex(), getTypeString(constant.getTag()), getValueString(constant));
        writer.decrementTabCount();
        writer.println("}\n");
    }

    private String getTypeString(int tag) {
        switch (tag) {
            case CONSTANT_CLASS:
                return "Class";
            case CONSTANT_FIELD_REF:
                return "FRef";
            case CONSTANT_METHOD_REF:
                return "MRef";
            case CONSTANT_INTERFACE_METHOD_REF:
                return "IMRef";
            case CONSTANT_STRING:
                return "Str";
            case CONSTANT_INTEGER:
                return "Int";
            case CONSTANT_FLOAT:
                return "Float";
            case CONSTANT_LONG:
                return "Long";
            case CONSTANT_DOUBLE:
                return "Double";
            case CONSTANT_NAME_AND_TYPE:
                return "NAT";
            case CONSTANT_UTF8:
                return "Utf8";
            case CONSTANT_METHOD_HANDLE:
                return "MethodHandle";
            case CONSTANT_METHOD_TYPE:
                return "MethodType";
            case CONSTANT_INVOKE_DYNAMIC:
                return "InvokeDynamic";
        }
        return "???";
    }

    private String getValueString(Constant constant) {
        if (constant instanceof ReferenceConstant) {
            ReferenceConstant const0 = (ReferenceConstant) constant;
            return String.format("#%d", const0.getIndex());
        } else if (constant instanceof WideReferenceConstant) {
            WideReferenceConstant const0 = (WideReferenceConstant) constant;
            return String.format("#%d, #%d", const0.getIndex1(), const0.getIndex2());
        } else if (constant instanceof IntegralConstant) {
            IntegralConstant const0 = (IntegralConstant) constant;
            return String.valueOf(const0.isFloat() ? Float.intBitsToFloat(const0.getValue()) : const0.getValue());
        } else if (constant instanceof WideIntegralConstant) {
            WideIntegralConstant const0 = (WideIntegralConstant) constant;
            return String.valueOf(const0.isDouble() ? Double.longBitsToDouble(const0.getValue()) : const0.getValue());
        } else if (constant instanceof MethodHandleConstant) {
            MethodHandleConstant const0 = (MethodHandleConstant) constant;
            return String.format("%d, #%d", const0.getType(), const0.getIndex());
        } else if (constant instanceof Utf8Constant) {
            Utf8Constant const0 = (Utf8Constant) constant;
            return String.format("\"%s\"", const0.getValue());
        } else
            return "???";
    }
}
