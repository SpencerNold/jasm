package me.spencernold.jasm.intermediary.attributes.impl.bootstrap;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class BootstrapMethod implements ReadWriteable<ByteBuf> {

    private int methodReference;
    private short[] arguments;

    @Override
    public void read(ByteBuf buf) {
        methodReference = buf.readShort();
        int length = buf.readShort();
        arguments = new short[length];
        for (int i = 0; i < length; i++)
            arguments[i] = (short) buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(methodReference);
        buf.writeShort(arguments.length);
        for (short arg : arguments)
            buf.writeShort(arg);
    }

    public int getMethodReference() {
        return methodReference;
    }

    public short[] getArguments() {
        return arguments;
    }
}
