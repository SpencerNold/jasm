package me.spencernold.jasm.intermediary.attributes.impl.module;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class Provides implements ReadWriteable<ByteBuf> {

    private int index;
    private short[] withIndex;

    @Override
    public void read(ByteBuf buf) {
        index = buf.readShort();
        withIndex = new short[buf.readShort()];
        for (int i = 0; i < withIndex.length; i++)
            withIndex[i] = (short) buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(index);
        buf.writeShort(withIndex.length);
        for (short s : withIndex)
            buf.writeShort(s);
    }

    public int getIndex() {
        return index;
    }

    public short[] getWithIndices() {
        return withIndex;
    }
}
