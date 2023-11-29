package me.spencernold.assembler.parser;

import java.io.File;
import java.io.IOException;

import me.spencernold.assembler.Hex;
import me.spencernold.assembler.Main;
import me.spencernold.assembler.ObjectWriter;
import me.spencernold.jasm.ClassReader;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JReadable;
import me.spencernold.jasm.intermediary.JWritable;

public class JParser implements JReadable<File>, JWritable<ObjectWriter> {
	
	private final ConstWriter constWriter = new ConstWriter();
	private final InterfaceWriter interfaceWriter = new InterfaceWriter();
	private final FieldWriter fieldWriter = new FieldWriter();
	private final MethodWriter methodWriter = new MethodWriter();
	private final AttributeWriter attributeWriter = new AttributeWriter();

	private JClass jclass;
	
	@Override
	public void read(File file) {
		ClassReader reader;
		try {
			reader = new ClassReader(file);
			jclass = reader.read();
			constWriter.read(jclass);
			interfaceWriter.read(jclass);
			fieldWriter.read(jclass);
			methodWriter.read(jclass);
			attributeWriter.read(jclass);
		} catch (IOException e) {
			Main.error(e.getMessage());
		}
	}
	
	@Override
	public void write(ObjectWriter writer) {
		writer.printf(".version 0x%s\n", Hex.hexify(jclass.getVersion()));
		writer.printf(".access 0x%s\n", Hex.hexify(jclass.getAccess()));
		constWriter.write(writer);
		writer.printf(".class_name #%d\n", jclass.getClassNameIndex());
		writer.printf(".super_name #%d\n", jclass.getSuperNameIndex());
		interfaceWriter.write(writer);
		fieldWriter.write(writer);
		methodWriter.write(writer);
		attributeWriter.write(writer);
	}
}
