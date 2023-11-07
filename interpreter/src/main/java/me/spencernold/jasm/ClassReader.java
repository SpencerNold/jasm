package me.spencernold.jasm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.utils.InputStreams;

/**
 * Interprets a binary Java class file into a {@code JClass} in memory based off
 * of the Oracle JVM specification.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.ClassWriter
 * @see me.spencernold.jasm.intermediary.JClass
 */
public class ClassReader {

	private final byte[] bytes;

	/**
	 * Passes a class buffer to the reader to be interpreted into intermediary
	 * representation.
	 * 
	 * @param bytes class buffer
	 */
	public ClassReader(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * Passes an input stream to the reader to be interpreted into intermediary
	 * representation. This stream is completely read into memory before it is
	 * interpreted.
	 * 
	 * @param input input stream of the class buffer
	 * @throws IOException throws an exception should the stream fail to be read
	 *                     into memory
	 */
	public ClassReader(InputStream input) throws IOException {
		this(InputStreams.readAllBytes(input));
	}

	/**
	 * Passes an input stream to the reader to be interpreted into intermediary
	 * representation. This File is completely read into memory before it is
	 * interpreted.
	 * 
	 * @param file file input of the class buffer
	 * @throws FileNotFoundException throws an exception if the file does not
	 *                               already exist
	 * @throws IOException           throws an exception should the file fail to be
	 *                               read into memory
	 */
	public ClassReader(File file) throws FileNotFoundException, IOException {
		this(new FileInputStream(file));
	}

	/**
	 * Begins the interpretation phase of the class in memory.
	 * 
	 * @return {@code JClass} interpretation in memory of the JVM class
	 */
	public JClass read() {
		JClass jclass = new JClass();
		ByteBuf buf = DynamicByteBuf.wrap(bytes);
		jclass.read(buf);
		return jclass;
	}
}
