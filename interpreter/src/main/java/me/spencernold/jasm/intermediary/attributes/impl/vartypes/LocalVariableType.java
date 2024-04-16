package me.spencernold.jasm.intermediary.attributes.impl.vartypes;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class LocalVariableType implements ReadWriteable<ByteBuf> {

    private int startPc;
    private int length;
    private int nameIndex;
    private int signatureIndex;
    private int index;

    @Override
    public void read(ByteBuf buf) {
        startPc = buf.readShort();
        length = buf.readShort();
        nameIndex = buf.readShort();
        signatureIndex = buf.readShort();
        index = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(startPc);
        buf.writeShort(length);
        buf.writeShort(nameIndex);
        buf.writeShort(signatureIndex);
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

    public void setLength(int length) {
        this.length = length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public void setSignatureIndex(int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
