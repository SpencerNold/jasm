package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class SourceDebugExtensionAttribute implements Body {

    private byte[] debugExtension;

    @Override
    public void read(ByteBuf buf) {
        debugExtension = buf.getRawBuffer();
        buf.setRawBuffer(new byte[0]);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.write(debugExtension);
    }

    public byte[] getDebugExtension() {
        return debugExtension;
    }
}
