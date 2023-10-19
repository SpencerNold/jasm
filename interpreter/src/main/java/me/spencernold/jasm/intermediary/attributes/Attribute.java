package me.spencernold.jasm.intermediary.attributes;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.exceptions.ClassFormatException;
import me.spencernold.jasm.exceptions.ClassFormatException.Type;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.code.CodeAttribute;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.Utf8Constant;

public class Attribute implements ReadWriteable {

	private final JClass jclass;
	private int nameIndex;
	private Body body;
	
	public Attribute(JClass jclass) {
		this.jclass = jclass;
	}
	
	public int getNameIndex() {
		return nameIndex;
	}
	
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	
	public Body getBody() {
		return body;
	}
	
	public <T extends Body> T getBody(Class<T> clazz) {
		return clazz.cast(body);
	}
	
	public <T extends Body> boolean isInstanceOf(Class<T> clazz) {
		return clazz.isInstance(body);
	}
	
	@Override
	public void read(ByteBuf buf) {
		nameIndex = buf.readShort();
		int length = buf.readInt();
		ByteBuf buf0 = new ByteBuf(buf.read(length));
		Constant constant = jclass.getConstPool().get(nameIndex);
		if (!constant.isUtf8())
			throw new ClassFormatException(Type.MALFORMED, String.format("attribute points to a constant which is not utf8: 0x%s", Integer.toUnsignedString(nameIndex, 16)));
		String value = ((Utf8Constant) constant).getValue();
		switch (value) {
		case Opcodes.ATTR_CODE:
			body = new CodeAttribute(jclass);
			break;
		default:
			body = new GenericBody();
			break;
		}
		body.read(buf0);
	}
	
	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(nameIndex);
		ByteBuf buf0 = new ByteBuf();
		body.write(buf0);
		byte[] data = buf0.getRawBuffer();
		buf.writeInt(data.length);
		buf.write(data);
	}
}
