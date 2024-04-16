package me.spencernold.assembler;

import me.spencernold.assembler.options.OptionContext;
import me.spencernold.assembler.options.OptionParser;
import me.spencernold.assembler.options.impl.IntegerOptionType;
import me.spencernold.assembler.writer.AssemblyWriter;
import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.jasm.ClassReader;
import me.spencernold.jasm.intermediary.JClass;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		OptionParser parser = new OptionParser(args);
		parser.register("tab", IntegerOptionType.class);
		OptionContext context = parser.parse();
		try {
			ClassReader reader = new ClassReader(new File("/Users/spencernold/Test.class"));
			JClass jclass = reader.read();

			StringWriter string = new StringWriter(context);
			AssemblyWriter writer = new AssemblyWriter(jclass);
			writer.write(string);
			System.out.println(string);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
