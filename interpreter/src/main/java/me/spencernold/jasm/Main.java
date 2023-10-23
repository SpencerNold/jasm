package me.spencernold.jasm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.attributes.CodeAttribute;
import me.spencernold.jasm.intermediary.code.instructions.Instruction;

public class Main {

	// Gives lower level bytecode control
	// More simple and explicit than the normal ASM libraries
	// Faster?
	// designed to be easier to use by people who understand JVM bytecode
	
	public static void main(String[] args) {
		ByteBuf buf = new ByteBuf();
		buf.writeInt(512);
		try {
			if (args.length != 2) {
				System.err.println("For testing purposes, please input a class file and an output file");
				return;
			}
			File f1 = new File(args[0]);
			File f2 = new File(args[1]);
			ClassReader reader = new ClassReader(f1);
			JClass jclass = reader.read();
			
			JMethod method = jclass.getMethodPool().getMethods().getFirst();
			CodeAttribute code = method.getAttributePool().getAttributeOf(CodeAttribute.class);
			LinkedList<Instruction> instructions = code.getInstructions().getCode().getInstructions();
			instructions.size();
			
			ClassWriter writer = new ClassWriter(jclass);
			writer.write(f2);
			
			System.out.println(compare(f1, f2));
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
