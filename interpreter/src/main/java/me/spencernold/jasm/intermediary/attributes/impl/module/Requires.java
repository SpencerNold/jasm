package me.spencernold.jasm.intermediary.attributes.impl.module;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class Requires implements ReadWriteable<ByteBuf> {

    private int index;
    private int flags;
    private int version;

    @Override
    public void read(ByteBuf buf) {
        index = buf.readShort();
        flags = buf.readShort();
        version = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(index);
        buf.writeShort(flags);
        buf.writeShort(version);
    }

    public int getIndex() {
        return index;
    }

    public int getFlags() {
        return flags;
    }

    public int getVersion() {
        return version;
    }
}
