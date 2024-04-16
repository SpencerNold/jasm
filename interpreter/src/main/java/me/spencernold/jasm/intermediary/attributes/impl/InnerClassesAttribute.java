package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.impl.aic.InnerClassTable;
import me.spencernold.jasm.intermediary.attributes.Body;

public class InnerClassesAttribute implements Body {

    private final InnerClassTable table;

    public InnerClassesAttribute() {
        this.table = new InnerClassTable();
    }

    @Override
    public void read(ByteBuf buf) {
        table.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        table.write(buf);
    }

    public InnerClassTable getTable() {
        return table;
    }
}
