package me.spencernold.assembler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ObjectWriter {

	private final PrintStream stream;
	
	private int tabCount = 0;
	
	public ObjectWriter(OutputStream stream) {
		this(stream instanceof PrintStream ? ((PrintStream) stream) : new PrintStream(stream));
	}
	
	public ObjectWriter(File file) throws FileNotFoundException {
		this(new FileOutputStream(file));
	}
	
	private ObjectWriter(PrintStream stream) {
		this.stream = stream;
	}
	
	public int getTabCount() {
		return tabCount;
	}
	
	public void setTabCount(int tabCount) {
		this.tabCount = tabCount;
	}
	
	public void incrementTabCount() {
		this.tabCount++;
	}
	
	public void decrementTabCount() {
		this.tabCount--;
	}
	
	public void println(String str) {
		stream.print(Strings.generateStringWithNCharacters(tabCount, '\t'));
		stream.println(str);
	}
	
	public void printlnRaw(String str) {
		stream.println(str);
	}
	
	public void printf(String format, Object... args) {
		stream.print(Strings.generateStringWithNCharacters(tabCount, '\t'));
		stream.printf(format, args);
	}
	
	public void printfRaw(String format, Object... args) {
		stream.printf(format, args);
	}
}
