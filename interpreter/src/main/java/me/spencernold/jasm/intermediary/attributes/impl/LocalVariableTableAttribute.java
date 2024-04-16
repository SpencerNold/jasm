package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.variables.LocalVariableTable;

public class LocalVariableTableAttribute implements Body {

    private final LocalVariableTable localVariableTable;

    public LocalVariableTableAttribute() {
        this.localVariableTable = new LocalVariableTable();
    }

    @Override
    public void read(ByteBuf buf) {
        localVariableTable.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        localVariableTable.write(buf);
    }

    public LocalVariableTable getLocalVariableTable() {
        return localVariableTable;
    }
}
