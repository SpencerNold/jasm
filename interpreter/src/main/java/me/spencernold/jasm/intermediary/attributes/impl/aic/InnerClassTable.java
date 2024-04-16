package me.spencernold.jasm.intermediary.attributes.impl.aic;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class InnerClassTable implements ReadWriteable<ByteBuf> {

    private InnerClass[] classes;

    @Override
    public void read(ByteBuf buf) {
        int count = buf.readShort();
        classes = new InnerClass[count];
        for (int i = 0; i < count; i++) {
            InnerClass clazz = new InnerClass();
            clazz.read(buf);
            classes[i] = clazz;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(classes.length);
        for (InnerClass clazz : classes)
            clazz.write(buf);
    }

    public InnerClass[] getClasses() {
        return classes;
    }
}
