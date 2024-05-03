package me.spencernold.jasm.intermediary.pools;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.constants.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Constant Pool of the JVM class file, where constant values such as class
 * names, field names, and other constants are stored.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 */
public class ConstPool implements ReadWriteable<ByteBuf>, Iterable<Constant> {

	private final ArrayList<Constant> constants = new ArrayList<>();

	/**
	 * Gets a constant at an index equal to the numerical value of the constant
	 * pool. In the JVM, constants are stored starting at index 1 rather than index
	 * 0, so the corresponding constant pool {@code index} would be
	 * {@code index -1}.
	 * 
	 * @param index index into the constant pool
	 * @return constant at the index in the constant pool
	 */
	public Constant get(int index) {
		return constants.get(index - 1);
	}

	public String getStringFromReferenceConstant(int index) {
		Constant constant = get(index);
		if (!(constant instanceof ReferenceConstant))
			return null;
		ReferenceConstant refConst = (ReferenceConstant) constant;
		constant = get(refConst.getIndex());
		if (!(constant instanceof Utf8Constant))
			return null;
		return ((Utf8Constant) constant).getValue();
	}

	public <T extends Constant> T getAsType(Class<T> type, int index) {
		Constant constant = get(index);
		if (!type.isInstance(constant))
			return null;
		return type.cast(constant);
	}

	/**
	 * Adds a constant to the current and existing constant pool.
	 * 
	 * @param constant constant to be added to the pool
	 * @return the constant pool index of the constant (array {@code index + 1}
	 */
	public int add(Constant constant) {
		constants.add(constant);
		int index = constants.size();
		constant.setConstPoolIndex(index);
		return index;
	}

	/**
	 * @return ArrayList of the constants in the pool
	 */
	public ArrayList<Constant> getConstants() {
		return constants;
	}

	public Stream<Constant> getConstantsByType(int type) {
		return getConstants(constant -> constant.getTag() == type);
	}

	public <T extends Constant> Stream<T> getConstantsByType(Class<T> type) {
		return getConstants(type::isInstance).map(type::cast);
	}

	public Stream<Constant> getConstants(Predicate<? super Constant> predicate) {
		return constants.stream().filter(predicate);
	}
	
	/**
	 * @return size of the internal ArrayList
	 */
	public int size() {
		return constants.size();
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
				add(new ReferenceConstant(tag, buf.readShort()));
				break;
			case Opcodes.CONSTANT_FIELD_REF:
			case Opcodes.CONSTANT_METHOD_REF:
			case Opcodes.CONSTANT_INTERFACE_METHOD_REF:
			case Opcodes.CONSTANT_NAME_AND_TYPE:
			case Opcodes.CONSTANT_INVOKE_DYNAMIC:
				add(new WideReferenceConstant(tag, buf.readShort(), buf.readShort()));
				break;
			case Opcodes.CONSTANT_INTEGER:
			case Opcodes.CONSTANT_FLOAT:
				add(new IntegralConstant(tag, buf.readInt()));
				break;
			case Opcodes.CONSTANT_LONG:
			case Opcodes.CONSTANT_DOUBLE:
				add(new WideIntegralConstant(tag, buf.readLong()));
				add(new PlaceholderConstant()); // Both take up 2 constant pool slots
				i++;
				break;
			case Opcodes.CONSTANT_METHOD_HANDLE:
				add(new MethodHandleConstant(tag, buf.readByte(), buf.readShort()));
				break;
			case Opcodes.CONSTANT_UTF8:
				add(new Utf8Constant(tag, new String(buf.read(buf.readShort()), StandardCharsets.UTF_8)));
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

	@Override
	public Iterator<Constant> iterator() {
		return constants.iterator();
	}
}
