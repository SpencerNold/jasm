package me.spencernold.assembler.parser.attributes;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.assembler.parser.ParserWriter;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.StackMapTableAttribute;

public class StackMapTableAttributeWriter implements ParserWriter<Attribute> {

	private StackMapTableAttribute body;
	
	@Override
	public void read(Attribute attribute) {
		body = attribute.getBody(StackMapTableAttribute.class);
	}
	
	@Override
	public void write(ObjectWriter writer) {
		
	}
}
