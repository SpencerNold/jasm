package me.spencernold.jasm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import me.spencernold.jasm.intermediary.JClass;

public class ClassWriter {

	private final JClass jclass;
	
	public ClassWriter(JClass jclass) {
		this.jclass = jclass;
	}
	
	public byte[] write() {
		ByteBuf buf = new ByteBuf();
		jclass.write(buf);
		return buf.getRawBuffer();
	}
	
	public void write(OutputStream output) throws IOException {
		output.write(write());
	}
	
	public void write(File file) throws IOException {
		FileOutputStream output = new FileOutputStream(file);
		write(output);
		output.close();
	}
}
