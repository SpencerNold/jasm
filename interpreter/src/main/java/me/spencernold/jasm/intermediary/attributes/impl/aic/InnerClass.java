package me.spencernold.jasm.intermediary.attributes.impl.aic;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class InnerClass implements ReadWriteable<ByteBuf> {

    private int innerClassInfoIndex;
    private int outerClassInfoIndex;
    private int nameIndex;
    private int access;

    @Override
    public void read(ByteBuf buf) {
        innerClassInfoIndex = buf.readShort();
        outerClassInfoIndex = buf.readShort();
        nameIndex = buf.readShort();
        access = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(innerClassInfoIndex);
        buf.writeShort(outerClassInfoIndex);
        buf.writeShort(nameIndex);
        buf.writeShort(access);
    }

    public int getInnerClassInfoIndex() {
        return innerClassInfoIndex;
    }

    public void setInnerClassInfoIndex(int index) {
        this.innerClassInfoIndex = index;
    }

    public int getOuterClassInfoIndex() {
        return outerClassInfoIndex;
    }

    public void setOuterClassInfoIndex(int outerClassInfoIndex) {
        this.outerClassInfoIndex = outerClassInfoIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
}
