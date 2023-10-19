package me.spencernold.jasm.intermediary.pools;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.IntegralConstant;
import me.spencernold.jasm.intermediary.constants.MethodHandleConstant;
import me.spencernold.jasm.intermediary.constants.ReferenceConstant;
import me.spencernold.jasm.intermediary.constants.Utf8Constant;
import me.spencernold.jasm.intermediary.constants.WideIntegralConstant;
import me.spencernold.jasm.intermediary.constants.WideReferenceConstant;

public class ConstPool implements ReadWriteable {

	private final ArrayList<Constant> constants = new ArrayList<>();

	public Constant get(int index) {
		return constants.get(index - 1);
	}
	
	public int add(Constant constant) {
		constants.add(constant);
		return constants.size();
	}
	
	public ArrayList<Constant> getConstants() {
		return constants;
	}
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort() - 1;
		for (int i = 0; i < size; i++) {
			int tag = buf.readByte();
			switch (tag) {
			case Opcodes.CONSTANT_CLASS:
			case Opcodes.CONSTANT_STRING:
			case Opcodes.CONSTANT_METHOD_TYPE:
				constants.add(new ReferenceConstant(tag, buf.readShort()));
				break;
			case Opcodes.CONSTANT_FIELD_REF:
			case Opcodes.CONSTANT_METHOD_REF:
			case Opcodes.CONSTANT_INTERFACE_METHOD_REF:
			case Opcodes.CONSTANT_NAME_AND_TYPE:
			case Opcodes.CONSTANT_INVOKE_DYNAMIC:
				constants.add(new WideReferenceConstant(tag, buf.readShort(), buf.readShort()));
				break;
			case Opcodes.CONSTANT_INTEGER:
			case Opcodes.CONSTANT_FLOAT:
				constants.add(new IntegralConstant(tag, buf.readInt()));
				break;
			case Opcodes.CONSTANT_LONG:
			case Opcodes.CONSTANT_DOUBLE:
				constants.add(new WideIntegralConstant(tag, buf.readLong()));
				break;
			case Opcodes.CONSTANT_METHOD_HANDLE:
				constants.add(new MethodHandleConstant(tag, buf.readByte(), buf.readShort()));
				break;
			case Opcodes.CONSTANT_UTF8:
				constants.add(new Utf8Constant(tag, new String(buf.read(buf.readShort()), StandardCharsets.UTF_8)));
				break;
			default:
				throw new ClassFormatException(Type.MALFORMED);
			}
		}
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(constants.size() + 1);
		for (Constant constant : constants) {
			buf.writeByte(constant.getTag());
			constant.write(buf);
		}
	}
}
