package me.spencernold.jasm;

/**
 * Abstraction of a byte buffer which uses the big endian byte order and first-in-first-out.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.DynamicByteBuf
 */
public abstract class ByteBuf {

	/**
	 * Appends n bytes to the end of the buffer.
	 * 
	 * @param bytes bytes to be written
	 */
	public abstract void write(byte[] bytes);

	/**
	 * Reads the next n bytes from the buffer in first-in-first-out order.
	 * 
	 * @param n amount of bytes to be read
	 * @return a byte array of size n
	 */
	public abstract byte[] read(int n);

	/**
	 * Gets the bytes currently to be read or already written to the buffer.
	 * 
	 * @return bytes in the buffer
	 */
	public abstract byte[] getRawBuffer();

	/**
	 * Sets the raw buffer in the byte buffer.
	 * 
	 * @param bytes bytes to be set
	 */
	public abstract void setRawBuffer(byte[] bytes);

	/**
	 * @return true if there are no bytes left in the buffer, false if there are
	 */
	public abstract boolean isEmpty();
	
	/**
	 * @return how many bytes are left in the buffer
	 */
	public abstract int getRemainingBytes();

	/**
	 * Writes a single 8-bit integer to the byte buffer.
	 * 
	 * @param b byte to be written
	 */
	public void writeByte(int b) {
		write(new byte[] { (byte) b });
	}

	/**
	 * Writes a single 16-bit integer to the byte buffer in big endian order.
	 * 
	 * @param s short to be written
	 */
	public void writeShort(int s) {
		byte[] bytes = new byte[2];
		bytes[1] = (byte) ((s >> 0) & 0xFF);
		bytes[0] = (byte) ((s >> 8) & 0xFF);
		write(bytes);
	}

	/**
	 * Writes a single 32-bit integer to the byte buffer in big endian order.
	 * 
	 * @param i int to be written
	 */
	public void writeInt(int i) {
		byte[] bytes = new byte[4];
		bytes[3] = (byte) ((i >> 0) & 0xFF);
		bytes[2] = (byte) ((i >> 8) & 0xFF);
		bytes[1] = (byte) ((i >> 16) & 0xFF);
		bytes[0] = (byte) ((i >> 24) & 0xFF);
		write(bytes);
	}

	/**
	 * Writes a single 64-bit integer to the byte buffer in big endian order.
	 * 
	 * @param l long to be written
	 */
	public void writeLong(long l) {
		byte[] bytes = new byte[8];
		bytes[7] = (byte) ((l >> 0) & 0xFF);
		bytes[6] = (byte) ((l >> 8) & 0xFF);
		bytes[5] = (byte) ((l >> 16) & 0xFF);
		bytes[4] = (byte) ((l >> 24) & 0xFF);
		bytes[3] = (byte) ((l >> 32) & 0xFF);
		bytes[2] = (byte) ((l >> 40) & 0xFF);
		bytes[1] = (byte) ((l >> 48) & 0xFF);
		bytes[0] = (byte) ((l >> 56) & 0xFF);
		write(bytes);
	}

	/**
	 * Reads a single 8-bit integer from the byte buffer.
	 * 
	 * @return next byte in the buffer
	 */
	public int readByte() {
		return Byte.toUnsignedInt(read(1)[0]);
	}

	/**
	 * Reads a single 16-bit integer from the byte buffer in big endian order.
	 * 
	 * @return next short in the buffer
	 */
	public int readShort() {
		byte[] bytes = read(2);
		return (short) (((bytes[0] & 0xFF) << 8) | ((bytes[1] & 0xFF) << 0));
	}

	/**
	 * Reads a single 32-bit integer from the byte buffer in big endian order.
	 * 
	 * @return next int in the buffer
	 */
	public int readInt() {
		byte[] bytes = read(4);
		return ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8)
				| ((bytes[3] & 0xFF) << 0);
	}

	/**
	 * Reads a single 64-bit integer from the byte buffer in big enaian order.
	 * 
	 * @return next long in the buffer
	 */
	public long readLong() {
		byte[] bytes = read(8);
		return ((bytes[0] & 0xFF) << 56) | ((bytes[1] & 0xFF) << 48) | ((bytes[2] & 0xFF) << 40)
				| ((bytes[3] & 0xFF) << 32) | ((bytes[4] & 0xFF) << 24) | ((bytes[5] & 0xFF) << 16)
				| ((bytes[6] & 0xFF) << 8) | ((bytes[7] & 0xFF) << 0);
	}
}
