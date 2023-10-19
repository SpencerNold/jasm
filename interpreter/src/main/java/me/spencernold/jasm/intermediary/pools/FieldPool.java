package me.spencernold.jasm.intermediary.pools;

import java.util.LinkedList;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JField;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class FieldPool implements ReadWriteable {
	
	private final JClass jclass;
	private final LinkedList<JField> fields = new LinkedList<>();
	
	public FieldPool(JClass jclass) {
		this.jclass = jclass;
	}
	
	public LinkedList<JField> getFields() {
		return fields;
	}

	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		for (int i = 0; i < size; i++) {
			JField jfield = new JField(jclass);
			jfield.read(buf);
			fields.add(jfield);
		}
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeShort(fields.size());
		for (JField jfield : fields)
			jfield.write(buf);
	}
}
