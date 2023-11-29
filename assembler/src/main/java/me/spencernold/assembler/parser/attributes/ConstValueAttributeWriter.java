package me.spencernold.assembler.parser.attributes;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.assembler.parser.ParserWriter;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.ConstValueAttribute;

public class ConstValueAttributeWriter implements ParserWriter<Attribute> {

	private ConstValueAttribute body;
	
	@Override
	public void read(Attribute attribute) {
		body = attribute.getBody(ConstValueAttribute.class);
	}

	@Override
	public void write(ObjectWriter writer) {
		
	}
}
