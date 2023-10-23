package me.spencernold.jasm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ByteBuf {
	
	// TODO Have the size be dynamic, double everytime more space is needed (have hard-coded limit on MAX_SIZE)
	// TODO have a LONG count the current index in reading and writing
	// TODO implement my own number-byte converter for BIG_ENDIAN to do away with the slow java ByteBuffer code

	private byte[] buffer;
	
	public ByteBuf(byte[] buffer) {
		this.buffer = buffer;
	}
	
	public ByteBuf() {
		this(new byte[0]);
	}
	
	public void write(byte[] bytes) {
		byte[] buf = new byte[buffer.length + bytes.length];
		System.arraycopy(buffer,  0, buf,  0, buffer.length);
		System.arraycopy(bytes, 0, buf, buffer.length, bytes.length);
		buffer = buf;
	}
	
	public void writeByte(int b) {
		write(new byte[] { (byte) b });
	}
	
	public void writeShort(int s) {
		write(ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort((short) s).array());
	}
	
	public void writeInt(int i) {
		write(ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(i).array());
	}
	
	public void writeLong(long l) {
		write(ByteBuffer.allocate(8).order(ByteOrder.BIG_ENDIAN).putLong(l).array());
	}
	
	public byte[] read(int n) {
		if (n > buffer.length) {
			byte[] bytes = new byte[n];
			System.arraycopy(buffer, 0, bytes, 0, buffer.length);
			buffer = new byte[0];
			return bytes;
		}
		byte[] bytes = Arrays.copyOfRange(buffer, 0, n);
		buffer = Arrays.copyOfRange(buffer, n, buffer.length);
		return bytes;
	}
	
	public byte readByte() {
		return read(1)[0];
	}
	
	public short readShort() {
		return ByteBuffer.wrap(read(2)).order(ByteOrder.BIG_ENDIAN).getShort();
	}
	
	public int readInt() {
		return ByteBuffer.wrap(read(4)).order(ByteOrder.BIG_ENDIAN).getInt();
	}
	
	public long readLong() {
		return ByteBuffer.wrap(read(8)).order(ByteOrder.BIG_ENDIAN).getLong();
	}
	
	public byte[] getRawBuffer() {
		return buffer;
	}
	
	public void setRawBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
	
	public boolean isEmpty() {
		return buffer.length == 0;
	}
}
