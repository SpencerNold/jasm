package me.spencernold.jasm.intermediary.attributes.impl.lines;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class LineNumberTable implements ReadWriteable<ByteBuf> {

    private LineNumberElement[] elements;

    @Override
    public void read(ByteBuf buf) {
        int length = buf.readShort();
        elements = new LineNumberElement[length];
        for (int i = 0; i < length; i++) {
            LineNumberElement element = new LineNumberElement();
            element.read(buf);
            elements[i] = element;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(elements.length);
        for (LineNumberElement element : elements)
            element.write(buf);
    }

    public LineNumberElement[] getElements() {
        return elements;
    }
}
