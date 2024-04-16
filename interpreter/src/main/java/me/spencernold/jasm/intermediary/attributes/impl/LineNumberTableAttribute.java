package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.lines.LineNumberTable;

public class LineNumberTableAttribute implements Body {

    private final LineNumberTable table;

    public LineNumberTableAttribute() {
        this.table = new LineNumberTable();
    }

    @Override
    public void read(ByteBuf buf) {
        table.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        table.write(buf);
    }

    public LineNumberTable getTable() {
        return table;
    }
}
