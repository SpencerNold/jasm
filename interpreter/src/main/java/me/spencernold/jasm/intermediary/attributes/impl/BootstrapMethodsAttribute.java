package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.bootstrap.BootstrapMethodTable;

public class BootstrapMethodsAttribute implements Body {

    private final BootstrapMethodTable table;

    public  BootstrapMethodsAttribute() {
        this.table = new BootstrapMethodTable();
    }

    @Override
    public void read(ByteBuf buf) {
        table.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        table.write(buf);
    }

    public BootstrapMethodTable getTable() {
        return table;
    }
}
