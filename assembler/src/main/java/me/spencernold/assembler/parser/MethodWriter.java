package me.spencernold.assembler.parser;

import java.util.LinkedList;

import me.spencernold.assembler.Hex;
import me.spencernold.assembler.ObjectWriter;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.pools.MethodPool;

public class MethodWriter implements ParserWriter<JClass> {
	
	private final AttributeWriter attributeWriter = new AttributeWriter();

	private MethodPool methodPool;
	
	@Override
	public void read(JClass jclass) {
		methodPool = jclass.getMethodPool();
	}
	
	@Override
	public void write(ObjectWriter writer) {
		writer.println(".methods");
		LinkedList<JMethod> methods = methodPool.getMethods();
		for (JMethod method : methods)
			writeMethod(writer, method);
	}
	
	private void writeMethod(ObjectWriter writer, JMethod method) {
		writer.println("{");
		writer.printf("\taccess: 0x%s\n", Hex.hexify(method.getAccess()));
		writer.printf("\tname: #%d // %s\n", method.getNameIndex(), method.getName());
		writer.printf("\tdescriptor: #%d // %s\n", method.getDescriptorIndex(), method.getDescriptor());
		attributeWriter.read(method);
		attributeWriter.write(writer);
		writer.println("}");
	}
}
