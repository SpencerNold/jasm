package me.spencernold.assembler;

import java.io.File;

import me.spencernold.assembler.parser.JParser;

public class Main {

	public static void main(String[] args) {
		for (String s : args) {
			if (!(s.endsWith(".class") || s.endsWith(".jasm")))
				error("please only input .class files and/or .jasm files");
			File file = new File(s);
			if (!file.exists())
				error("\"%s\" must exist for JASM to run", s);
			if (s.endsWith(".class")) {
				JParser parser = new JParser();
				parser.read(file);
				parser.write(new ObjectWriter(System.out));
			} else if (s.endsWith(".jasm")) {
				
			}
		}
	}
	
	// TODO Move this to some sort of logger class
	public static void error(String format, Object... args) {
		System.err.println(String.format(format, args));
		System.exit(1);
	}
}
