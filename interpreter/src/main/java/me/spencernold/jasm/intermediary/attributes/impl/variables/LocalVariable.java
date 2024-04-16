package me.spencernold.jasm.intermediary.attributes.impl.variables;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class LocalVariable implements ReadWriteable<ByteBuf> {

    private int startPc;
    private int length;
    private int nameIndex;
    private int descriptorIndex;
    private int index;

    @Override
    public void read(ByteBuf buf) {
        startPc = buf.readShort();
        length = buf.readShort();
        nameIndex = buf.readShort();
        descriptorIndex = buf.readShort();
        index = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(startPc);
        buf.writeShort(length);
        buf.writeShort(nameIndex);
        buf.writeShort(descriptorIndex);
        buf.writeShort(index);
    }

    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    public int getLength() {
        return length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getIndex() {
        return index;
    }
}
