package me.spencernold.assembler.parser.attributes.code;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.assembler.parser.ParserWriter;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.CodeAttribute;

public class CodeAttributeWriter implements ParserWriter<Attribute> {
	
	private final InstructionWriter instructionWriter = new InstructionWriter();
	
	private CodeAttribute body;

	@Override
	public void read(Attribute attribute) {
		body = attribute.getBody(CodeAttribute.class);
		instructionWriter.read(body);
	}

	@Override
	public void write(ObjectWriter writer) {
		writer.incrementTabCount();
		writer.printf("stack = %d, locals = %d\n", body.getMaxStack(), body.getMaxLocals());
		instructionWriter.write(writer);
		writer.decrementTabCount();
	}
}
