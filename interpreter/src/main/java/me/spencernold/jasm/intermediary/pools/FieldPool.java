package me.spencernold.jasm.intermediary.pools;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JField;
import me.spencernold.jasm.intermediary.ReadWriteable;

import java.util.ArrayList;
import java.util.Iterator;

public class FieldPool implements ReadWriteable<ByteBuf>, Iterable<JField> {

	private final JClass jclass;
	private ArrayList<JField> fields;

	public FieldPool(JClass jclass) {
		this.jclass = jclass;
	}

	/**
	 * Gets the fields currently stored in the field pool.
	 * 
	 * @return LinkedList instance containing the fields in the pool
	 */
	public ArrayList<JField> getFields() {
		return fields;
	}
	
	@Override
	public void read(ByteBuf buf) {
		int size = buf.readShort();
		fields = new ArrayList<>(size);
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

	@Override
	public Iterator<JField> iterator() {
		return fields.iterator();
	}
}
