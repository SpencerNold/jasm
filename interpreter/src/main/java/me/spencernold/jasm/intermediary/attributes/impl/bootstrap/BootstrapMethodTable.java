package me.spencernold.jasm.intermediary.attributes.impl.bootstrap;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;

public class BootstrapMethodTable implements ReadWriteable<ByteBuf> {

    private BootstrapMethod[] methods;

    @Override
    public void read(ByteBuf buf) {
        int length = buf.readShort();
        methods = new BootstrapMethod[length];
        for (int i = 0; i < length; i++) {
            BootstrapMethod method = new BootstrapMethod();
            method.read(buf);
            methods[i] = method;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(methods.length);
        for (BootstrapMethod method : methods)
            method.write(buf);
    }

    public BootstrapMethod[] getMethods() {
        return methods;
    }
}
