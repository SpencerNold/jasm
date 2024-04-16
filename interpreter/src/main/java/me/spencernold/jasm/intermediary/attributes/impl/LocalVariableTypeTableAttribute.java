package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.vartypes.LocalVariableTypeTable;

public class LocalVariableTypeTableAttribute implements Body {

    private final LocalVariableTypeTable table;

    public LocalVariableTypeTableAttribute() {
        this.table = new LocalVariableTypeTable();
    }

    @Override
    public void read(ByteBuf buf) {
        table.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        table.write(buf);
    }

    public LocalVariableTypeTable getTable() {
        return table;
    }
}
