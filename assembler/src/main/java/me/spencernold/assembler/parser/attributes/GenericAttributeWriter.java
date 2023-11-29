package me.spencernold.assembler.parser.attributes;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.assembler.parser.ParserWriter;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.GenericBody;

public class GenericAttributeWriter implements ParserWriter<Attribute> {
	
	private GenericBody body;
	
	@Override
	public void read(Attribute attribute) {
		body = attribute.getBody(GenericBody.class);
	}

	@Override
	public void write(ObjectWriter writer) {
		
	}
}
