package me.spencernold.jasm;

import java.util.Arrays;

/**
 * Implementation of the {@code ByteBuf} class where the byte buffer is
 * dynamically sized based on what is written to the buffer.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.DynamicByteBuf
 */
public class DynamicByteBuf extends ByteBuf {

	private byte[] buffer;
	private int pointer;
	private boolean reading;

	private DynamicByteBuf(int isize) {
		this(new byte[isize], false);
	}

	private DynamicByteBuf(byte[] buffer, boolean reading) {
		this.buffer = buffer;
		this.pointer = 0;
		this.reading = reading;
	}

	@Override
	public void write(byte[] bytes) {
		if (reading)
			throw new IllegalStateException("unnable to write: ByteBuf is in the reading state");
		while ((pointer + bytes.length) > buffer.length)
			reallocate();
		System.arraycopy(bytes, 0, buffer, pointer, bytes.length);
		pointer += bytes.length;
	}

	@Override
	public byte[] read(int n) {
		if (!reading)
			throw new IllegalStateException("unnable to read: ByteBuf is in the writing state");
		if ((pointer + n) > buffer.length)
			throw new IllegalStateException("unnable to read: insufficient bytes in the buffer");
		byte[] bytes = new byte[n];
		System.arraycopy(buffer, pointer, bytes, 0, n);
		pointer += n;
		return bytes;
	}

	private void reallocate() {
		int size = buffer.length * 2;
		byte[] buf = new byte[size];
		System.arraycopy(buffer, 0, buf, 0, buffer.length);
		this.buffer = buf;
	}

	@Override
	public byte[] getRawBuffer() {
		if (reading)
			return Arrays.copyOfRange(buffer, pointer, buffer.length);
		else
			return Arrays.copyOfRange(buffer, 0, pointer);
	}

	@Override
	public void setRawBuffer(byte[] bytes) {
		this.buffer = bytes;
	}

	@Override
	public boolean isEmpty() {
		return pointer == buffer.length;
	}
	
	@Override
	public int getRemainingBytes() {
		return buffer.length - pointer;
	}
	
	public int getPointer() {
		return pointer;
	}
	
	/**
	 * Flips the byte buffer from a reading mode to a writing mode and vise versa.
	 */
	public void flip() {
		// TODO Solve issue of getRawBuffer caused when flipping
		reading = !reading;
		pointer = 0;
	}
	
	/**
	 * Wraps a byte array into a DynamicByteBuf
	 * 
	 * @param bytes bytes to be wrapped
	 * @return DynamicByteBuf reference
	 */
	public static DynamicByteBuf wrap(byte[] bytes) {
		return new DynamicByteBuf(bytes, true);
	}

	/**
	 * Allocates a new DynamicByteBuf with an internal buffer size of 8. This
	 * initial size is completely internal, and has no effect on the actual data.
	 * 
	 * @return DynamicByteBuf reference
	 */
	public static DynamicByteBuf allocate() {
		return new DynamicByteBuf(8);
	}

	/**
	 * Allocates a new DynamicByteBuf with an input internal buffer size. This
	 * initial size is completely internal, and has no effect on the actual data.
	 * This should be used as an optimization do decrease the amount of allocations
	 * which take place.
	 * 
	 * @param isize initial size of the buffer
	 * @return DynamicByteBuf reference
	 */
	public static DynamicByteBuf allocate(int isize) {
		if (isize < 1)
			isize = 1;
		return new DynamicByteBuf(isize);
	}
}
