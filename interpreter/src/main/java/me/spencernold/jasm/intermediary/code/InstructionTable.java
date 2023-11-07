package me.spencernold.jasm.intermediary.code;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.DynamicByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

/**
 * Instruction table which reads the Code from the Code attribute.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 */
public class InstructionTable implements ReadWriteable {

	private final Code code;

	public InstructionTable() {
		code = new Code();
	}

	/**
	 * @return Code body of the attribute
	 */
	public Code getCode() {
		return code;
	}

	@Override
	public void read(ByteBuf buf) {
		int length = buf.readInt();
		ByteBuf buf0 = DynamicByteBuf.wrap(buf.read(length));
		code.read(buf0);
	}

	@Override
	public void write(ByteBuf buf) {
		ByteBuf buf0 = DynamicByteBuf.allocate();
		code.write(buf0);
		byte[] data = buf0.getRawBuffer();
		buf.writeInt(data.length);
		buf.write(data);
	}
}
