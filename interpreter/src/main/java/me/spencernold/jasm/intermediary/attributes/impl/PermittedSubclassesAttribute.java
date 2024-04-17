package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class PermittedSubclassesAttribute implements Body {

    private short[] classes;

    @Override
    public void read(ByteBuf buf) {
        classes = new short[buf.readShort()];
        for (int i = 0; i < classes.length; i++)
            classes[i] = (short) buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(classes.length);
        for (short s : classes)
            buf.writeShort(s);
    }

    public short[] getClasses() {
        return classes;
    }
}
