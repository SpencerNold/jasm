package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class ExceptionsAttribute implements Body {

    private int[] exceptions;

    @Override
    public void read(ByteBuf buf) {
        int count = buf.readShort();
        exceptions = new int[count];
        for (int i = 0; i < count; i++)
            exceptions[i] = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(exceptions.length);
        for (int e : exceptions)
            buf.writeShort(e);
    }

    public int[] getExceptions() {
        return exceptions;
    }
}
