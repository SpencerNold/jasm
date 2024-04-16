package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class SourceFileAttribute implements Body {

    private int sourceFileIndex;

    @Override
    public void read(ByteBuf buf) {
        sourceFileIndex = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(sourceFileIndex);
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }
}
