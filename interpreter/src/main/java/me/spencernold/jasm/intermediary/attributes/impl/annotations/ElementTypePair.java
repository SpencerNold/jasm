package me.spencernold.jasm.intermediary.attributes.impl.annotations;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.utils.Pair;

public class ElementTypePair extends Pair<Integer, ElementValue> implements ReadWriteable<ByteBuf> {

    @Override
    public void read(ByteBuf buf) {
        setKey(buf.readShort()); // nameIndex
        ElementValue value = new ElementValue();
        value.read(buf);
        setValue(value);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(getKey());
        getValue().write(buf);
    }
}
