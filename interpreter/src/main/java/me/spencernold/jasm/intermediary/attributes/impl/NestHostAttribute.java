package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class NestHostAttribute implements Body {

    private int index;

    @Override
    public void read(ByteBuf buf) {
        index = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(index);
    }

    public int getIndex() {
        return index;
    }
}
