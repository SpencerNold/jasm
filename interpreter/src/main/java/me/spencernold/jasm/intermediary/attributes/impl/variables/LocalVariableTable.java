package me.spencernold.jasm.intermediary.attributes.impl.variables;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class LocalVariableTable implements ReadWriteable<ByteBuf> {

    private LocalVariable[] localVariables;

    @Override
    public void read(ByteBuf buf) {
        int length = buf.readShort();
        localVariables = new LocalVariable[length];
        for (int i = 0; i < length; i++) {
            LocalVariable variable = new LocalVariable();
            variable.read(buf);
            localVariables[i] = variable;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(localVariables.length);
        for (LocalVariable variable : localVariables)
            variable.write(buf);
    }

    public LocalVariable[] getLocalVariables() {
        return localVariables;
    }
}
