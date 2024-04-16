package me.spencernold.jasm.intermediary.attributes.impl.annotations;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class Annotation implements ReadWriteable<ByteBuf> {

    private int type;
    private ElementTypePair[] elementTypePairs;

    @Override
    public void read(ByteBuf buf) {
        type = buf.readShort();
        int length = buf.readShort();
        elementTypePairs = new ElementTypePair[length];
        for (int i = 0; i < length; i++) {
            ElementTypePair pair = new ElementTypePair();
            pair.read(buf);
            elementTypePairs[i] = pair;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(type);
        buf.writeShort(elementTypePairs.length);
        for (ElementTypePair pair : elementTypePairs)
            pair.write(buf);
    }

    public int getType() {
        return type;
    }

    public ElementTypePair[] getElementTypePairs() {
        return elementTypePairs;
    }
}
