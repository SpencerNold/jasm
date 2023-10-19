package me.spencernold.jasm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.utils.InputStreams;

public class ClassReader {

	private final byte[] bytes;
	
	public ClassReader(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public ClassReader(InputStream input) throws IOException {
		this(InputStreams.readAllBytes(input));
	}
	
	public ClassReader(File file) throws FileNotFoundException, IOException {
		this(new FileInputStream(file));
	}
	
	public JClass read() {
		JClass jclass = new JClass();
		ByteBuf buf = new ByteBuf(bytes);
		jclass.read(buf);
		return jclass;
	}
}
