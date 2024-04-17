package me.spencernold.jasm.intermediary.attributes.impl.record;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.attributes.Attribute;

public class RecordInfo implements ReadWriteable<ByteBuf> {

    private final JClass jclass;

    private int name;
    private int descriptor;
    private Attribute[] attributes;

    public RecordInfo(JClass jclass) {
        this.jclass = jclass;
    }

    @Override
    public void read(ByteBuf buf) {
        name = buf.readShort();
        descriptor = buf.readShort();
        attributes = new Attribute[buf.readShort()];
        for (int i = 0; i < attributes.length; i++) {
            Attribute attribute = new Attribute(jclass);
            attribute.read(buf);
            attributes[i] = attribute;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(name);
        buf.writeShort(descriptor);
        buf.writeShort(attributes.length);
        for (Attribute attribute : attributes)
            attribute.write(buf);
    }

    public int getName() {
        return name;
    }

    public int getDescriptor() {
        return descriptor;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }
}
