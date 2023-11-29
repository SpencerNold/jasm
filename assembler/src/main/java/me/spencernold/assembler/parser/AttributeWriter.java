package me.spencernold.assembler.parser;

import java.util.LinkedList;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.assembler.parser.attributes.ConstValueAttributeWriter;
import me.spencernold.assembler.parser.attributes.GenericAttributeWriter;
import me.spencernold.assembler.parser.attributes.StackMapTableAttributeWriter;
import me.spencernold.assembler.parser.attributes.code.CodeAttributeWriter;
import me.spencernold.jasm.Opcodes;
import me.spencernold.jasm.intermediary.AttributeElement;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JField;
import me.spencernold.jasm.intermediary.JMethod;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.pools.AttributePool;

public class AttributeWriter implements ParserWriter<AttributeElement> {
	
	private final GenericAttributeWriter genericAttributeWriter = new GenericAttributeWriter();
	private final ConstValueAttributeWriter constValueAttributeWriter = new ConstValueAttributeWriter();
	private final CodeAttributeWriter codeAttributeWriter = new CodeAttributeWriter();
	private final StackMapTableAttributeWriter stackMapTableAttributeWriter = new StackMapTableAttributeWriter();

	private AttributePool attributePool;
	private int indentsToAdd;
	
	@Override
	public void read(AttributeElement element) {
		attributePool = element.getAttributePool();
		if (element instanceof JClass)
			indentsToAdd = 0;
		else if (element instanceof JMethod || element instanceof JField)
			indentsToAdd = 1;
		else
			indentsToAdd = 2;
	}
	
	@Override
	public void write(ObjectWriter writer) {
		LinkedList<Attribute> attributes = attributePool.getAttributes();
		for (Attribute attribute : attributes)
			writeAttribute(writer, attribute);
	}
	
	private void writeAttribute(ObjectWriter writer, Attribute attribute) {
		String name = attribute.getName();
		for (int i = 0; i < indentsToAdd; i++)
			writer.incrementTabCount();
		writer.printf("%s:\n", name);
		switch (name) {
		case Opcodes.ATTR_CONSTANT_VALUE:
			constValueAttributeWriter.read(attribute);
			constValueAttributeWriter.write(writer);
			break;
		case Opcodes.ATTR_CODE:
			codeAttributeWriter.read(attribute);
			codeAttributeWriter.write(writer);
			break;
		case Opcodes.ATTR_STACK_MAP_TABLE:
			stackMapTableAttributeWriter.read(attribute);
			stackMapTableAttributeWriter.write(writer);
			break;
		default:
			genericAttributeWriter.read(attribute);
			genericAttributeWriter.write(writer);
			break;
		}
		for (int i = 0; i < indentsToAdd; i++)
			writer.decrementTabCount();
	}
}
