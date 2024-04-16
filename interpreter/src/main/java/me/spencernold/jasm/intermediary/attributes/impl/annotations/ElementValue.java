package me.spencernold.jasm.intermediary.attributes.impl.annotations;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class ElementValue implements ReadWriteable<ByteBuf> {

    private int tag;
    private int constValueIndex;
    private int typeNameIndex;
    private int constNameIndex;
    private int classInfoIndex;
    private Annotation annotation;
    private ElementValue[] values;

    @Override
    public void read(ByteBuf buf) {
        tag = buf.readByte();
        constValueIndex = buf.readShort();
        typeNameIndex = buf.readShort();
        constNameIndex = buf.readShort();
        classInfoIndex = buf.readShort();
        annotation = new Annotation();
        annotation.read(buf);
        int length = buf.readShort();
        values = new ElementValue[length];
        for (int i = 0; i < length; i++) {
            ElementValue value = new ElementValue();
            value.read(buf);
            values[i] = value;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(tag);
        buf.writeShort(constValueIndex);
        buf.writeShort(typeNameIndex);
        buf.writeShort(constNameIndex);
        buf.writeShort(classInfoIndex);
        annotation.write(buf);
        buf.writeShort(values.length);
        for (ElementValue value : values)
            value.write(buf);
    }

    public int getTag() {
        return tag;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public int getTypeNameIndex() {
        return typeNameIndex;
    }

    public int getConstNameIndex() {
        return constNameIndex;
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public ElementValue[] getValues() {
        return values;
    }
}
