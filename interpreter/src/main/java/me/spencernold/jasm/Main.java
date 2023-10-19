package me.spencernold.jasm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.code.CodeAttribute;

public class Main {

	// Gives lower level bytecode control
	// More simple and explicit than the normal ASM libraries
	// Faster?
	// designed to be easier to use by people who understand JVM bytecode
	
	public static void main(String[] args) {
		try {
			ClassReader reader = new ClassReader(new File("C:\\Users\\spenc\\Desktop\\jasm\\Test1.class"));
			JClass jclass = reader.read();
			
			JMethod method = jclass.getMethodPool().getMethods().getFirst();
			CodeAttribute code = method.getAttributePool().getAttributeOf(CodeAttribute.class);
			System.out.println(code.getMaxStack() + ", " + code.getMaxLocals());
			
			ClassWriter writer = new ClassWriter(jclass);
			writer.write(new File("C:\\Users\\spenc\\Desktop\\jasm\\Test1.class"));
			
			System.out.println(compare(new File("C:\\Users\\spenc\\Desktop\\jasm\\Test0.class"), new File("C:\\Users\\spenc\\Desktop\\jasm\\Test1.class")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean compare(File f1, File f2) throws IOException {
		byte[] b1 = Files.readAllBytes(f1.toPath());
		byte[] b2 = Files.readAllBytes(f2.toPath());
		if (b1.length != b2.length)
			return false;
		for (int i = 0; i < b1.length; i++) {
			if (b1[i] != b2[i])
				return false;
		}
		return true;
	}
}
