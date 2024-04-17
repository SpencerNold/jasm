package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.record.RecordInfo;

public class RecordAttribute implements Body {

    private final JClass jclass;

    private RecordInfo[] components;

    public RecordAttribute(JClass jclass) {
        this.jclass = jclass;
    }

    @Override
    public void read(ByteBuf buf) {
        components = new RecordInfo[buf.readShort()];
        for (int i = 0; i < components.length; i++) {
            RecordInfo component = new RecordInfo(jclass);
            component.read(buf);
            components[i] = component;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(components.length);
        for (RecordInfo component : components)
            component.write(buf);
    }
}
