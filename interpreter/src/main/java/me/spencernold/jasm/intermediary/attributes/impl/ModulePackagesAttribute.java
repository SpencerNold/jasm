package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class ModulePackagesAttribute implements Body {

    private short[] indices;

    @Override
    public void read(ByteBuf buf) {
        indices = new short[buf.readShort()];
        for (int i = 0; i < indices.length; i++)
            indices[i] = (short) buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(indices.length);
        for (short s : indices)
            buf.writeShort(s);
    }

    public short[] getIndices() {
        return indices;
    }
}
