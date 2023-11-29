package me.spencernold.assembler.parser;

import java.util.LinkedList;

import me.spencernold.assembler.Hex;
import me.spencernold.assembler.ObjectWriter;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JField;
import me.spencernold.jasm.intermediary.pools.FieldPool;

public class FieldWriter implements ParserWriter<JClass> {

	private final AttributeWriter attributeWriter = new AttributeWriter();
	private FieldPool fieldPool;
	
	@Override
	public void read(JClass jclass) {
		fieldPool = jclass.getFieldPool();
	}
	
	@Override
	public void write(ObjectWriter writer) {
		writer.println(".fields");
		LinkedList<JField> fields = fieldPool.getFields();
		for (JField field : fields)
			writeField(writer, field);
	}
	
	private void writeField(ObjectWriter writer, JField field) {
		writer.println("{");
		writer.incrementTabCount();
		writer.printf("access: 0x%s\n", Hex.hexify(field.getAccess()));
		writer.printf("name: #%d // %s\n", field.getNameIndex(), field.getName());
		writer.printf("descriptor: #%d // %s\n", field.getDescriptorIndex(), field.getDescriptor());
		if (field.hasAttributes()) {
			attributeWriter.read(field);
			attributeWriter.write(writer);
		}
		writer.decrementTabCount();
		writer.println("}");
	}
}
