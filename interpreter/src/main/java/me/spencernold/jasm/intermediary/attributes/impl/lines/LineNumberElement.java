package me.spencernold.jasm.intermediary.attributes.impl.lines;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class LineNumberElement implements ReadWriteable<ByteBuf> {

    private int startPc;
    private int lineNumber;

    @Override
    public void read(ByteBuf buf) {
        startPc = buf.readShort();
        lineNumber = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(startPc);
        buf.writeShort(lineNumber);
    }

    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
