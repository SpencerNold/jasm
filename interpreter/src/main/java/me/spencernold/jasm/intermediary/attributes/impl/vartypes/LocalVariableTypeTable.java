package me.spencernold.jasm.intermediary.attributes.impl.vartypes;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class LocalVariableTypeTable implements ReadWriteable<ByteBuf> {

    private LocalVariableType[] variableTypes;

    @Override
    public void read(ByteBuf buf) {
        int length = buf.readShort();
        variableTypes = new LocalVariableType[length];
        for (int i = 0; i < length; i++) {
            LocalVariableType variableType = new LocalVariableType();
            variableType.read(buf);
            variableTypes[i] = variableType;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(variableTypes.length);
        for (LocalVariableType variableType : variableTypes)
            variableType.write(buf);
    }

    public LocalVariableType[] getVariableTypes() {
        return variableTypes;
    }
}
