package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class EnclosingMethodAttribute implements Body {

    private int classIndex;
    private int methodIndex;

    @Override
    public void read(ByteBuf buf) {
        classIndex = buf.readShort();
        methodIndex = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(classIndex);
        buf.writeShort(methodIndex);
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }
}
