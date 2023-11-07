package me.spencernold.jasm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import me.spencernold.jasm.intermediary.JClass;

/**
 * Writer to write a {@code JClass} intermediary form from memory to a JVM
 * binary class file.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.ClassReader
 * @see me.spencernold.jasm.intermediary.JClass
 */
public class ClassWriter {

	private final JClass jclass;

	/**
	 * Constructs a {@code ClassWriter} with a {@code JClass} input to be written to
	 * a buffer, stream, or file.
	 * 
	 * @param jclass JClass intermediary
	 */
	public ClassWriter(JClass jclass) {
		this.jclass = jclass;
	}

	/**
	 * Writes the constructed {@code JClass} to a byte array.
	 * 
	 * @return bytes of the JVM binary class file
	 */
	public byte[] write() {
		ByteBuf buf = DynamicByteBuf.allocate();
		jclass.write(buf);
		return buf.getRawBuffer();
	}

	/**
	 * Writes the constructed {@code JClass} to an output stream. The entire byte
	 * buffer is written directly at once to the output stream.
	 * 
	 * @param output output stream for the buffer to be written to
	 * @throws IOException throws an exception should the stream fail to write
	 */
	public void write(OutputStream output) throws IOException {
		output.write(write());
	}

	/**
	 * Writes the constructed {@code JClass} to a file. The entire byte buffer is
	 * written directly at once to the file through a {@code FileOutputStream}
	 * 
	 * @param file file to have the class buffer be written to
	 * @throws FileNotFoundException throws an exception should the file not exist
	 * @throws IOException           throws an exception should the stream fail to
	 *                               write
	 */
	public void write(File file) throws FileNotFoundException, IOException {
		FileOutputStream output = new FileOutputStream(file);
		write(output);
		output.close();
	}
}
