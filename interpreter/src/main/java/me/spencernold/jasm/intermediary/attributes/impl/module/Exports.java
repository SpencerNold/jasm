package me.spencernold.jasm.intermediary.attributes.impl.module;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class Exports implements ReadWriteable<ByteBuf> {

    private int index;
    private int flags;
    private short[] toIndex;

    @Override
    public void read(ByteBuf buf) {
        index = buf.readShort();
        flags = buf.readShort();
        toIndex = new short[buf.readShort()];
        for (int i = 0; i < toIndex.length; i++)
            toIndex[i] = (short) buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(index);
        buf.writeShort(flags);
        buf.writeShort(toIndex.length);
        for (short s : toIndex)
            buf.writeShort(s);
    }

    public int getIndex() {
        return index;
    }

    public int getFlags() {
        return flags;
    }

    public short[] getToIndices() {
        return toIndex;
    }
}
