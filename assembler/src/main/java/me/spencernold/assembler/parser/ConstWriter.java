package me.spencernold.assembler.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.spencernold.assembler.Hex;
import me.spencernold.assembler.ObjectWriter;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.IntegralConstant;
import me.spencernold.jasm.intermediary.constants.MethodHandleConstant;
import me.spencernold.jasm.intermediary.constants.ReferenceConstant;
import me.spencernold.jasm.intermediary.constants.Utf8Constant;
import me.spencernold.jasm.intermediary.constants.WideIntegralConstant;
import me.spencernold.jasm.intermediary.constants.WideReferenceConstant;
import me.spencernold.jasm.intermediary.pools.ConstPool;

public class ConstWriter implements ParserWriter<JClass> {

	private final Map<Integer, String> translator = new HashMap<Integer, String>() {
		private static final long serialVersionUID = -4723905544405121778L;

		{
			put(Opcodes.CONSTANT_CLASS, "CLASS");
			put(Opcodes.CONSTANT_FIELD_REF, "FREF");
			put(Opcodes.CONSTANT_METHOD_REF, "MREF");
			put(Opcodes.CONSTANT_INTERFACE_METHOD_REF, "IMREF");
			put(Opcodes.CONSTANT_STRING, "STR");
			put(Opcodes.CONSTANT_INTEGER, "INT");
			put(Opcodes.CONSTANT_FLOAT, "FLOAT");
			put(Opcodes.CONSTANT_LONG, "INT_W");
			put(Opcodes.CONSTANT_DOUBLE, "FLOAT_W");
			put(Opcodes.CONSTANT_NAME_AND_TYPE, "NAT");
			put(Opcodes.CONSTANT_UTF8, "UTF8");
			put(Opcodes.CONSTANT_METHOD_HANDLE, "MHANDLE");
			put(Opcodes.CONSTANT_METHOD_TYPE, "MTYPE");
			put(Opcodes.CONSTANT_INVOKE_DYNAMIC, "INVOKE_D");
		}
	};

	private ConstPool constPool;

	@Override
	public void read(JClass jclass) {
		constPool = jclass.getConstPool();
	}

	@Override
	public void write(ObjectWriter writer) {
		writer.println(".constants");
		writer.incrementTabCount();
		ArrayList<Constant> constants = constPool.getConstants();
		for (int i = 0; i < constants.size(); i++) {
			Constant constant = constants.get(i);
			writeConstant(writer, (i + 1), constant);
		}
		writer.decrementTabCount();
	}

	private void writeConstant(ObjectWriter writer, int index, Constant constant) {
		String name = translator.get(constant.getTag());
		String value = null;
		switch (constant.getTag()) {
		case Opcodes.CONSTANT_CLASS:
		case Opcodes.CONSTANT_STRING:
		case Opcodes.CONSTANT_METHOD_TYPE:
			value = String.format("#%d", ((ReferenceConstant) constant).getIndex());
			break;
		case Opcodes.CONSTANT_FIELD_REF:
		case Opcodes.CONSTANT_METHOD_REF:
		case Opcodes.CONSTANT_INTERFACE_METHOD_REF:
		case Opcodes.CONSTANT_NAME_AND_TYPE:
		case Opcodes.CONSTANT_INVOKE_DYNAMIC:
			WideReferenceConstant wrc = (WideReferenceConstant) constant;
			value = String.format("#%d:%d", wrc.getIndex1(), wrc.getIndex2());
			break;
		case Opcodes.CONSTANT_INTEGER:
			value = String.valueOf(((IntegralConstant) constant).getValue());
			break;
		case Opcodes.CONSTANT_FLOAT:
			value = String.valueOf(((IntegralConstant) constant).getFloatingPointValue());
			break;
		case Opcodes.CONSTANT_LONG:
			value = String.valueOf(((WideIntegralConstant) constant).getValue());
			break;
		case Opcodes.CONSTANT_DOUBLE:
			value = String.valueOf(((WideIntegralConstant) constant).getFloatingPointValue());
			break;
		case Opcodes.CONSTANT_METHOD_HANDLE:
			MethodHandleConstant mhc = (MethodHandleConstant) constant;
			value = String.format("0x%s, #%d", Hex.hexify(mhc.getType()), mhc.getIndex());
			break;
		case Opcodes.CONSTANT_UTF8:
			value = String.format("\"%s\"", ((Utf8Constant) constant).getValue());
			break;
		default:
			throw new ClassFormatException(Type.MALFORMED);
		}
		writer.printf("#%d %s %s\n", index, name, value);
	}
}
