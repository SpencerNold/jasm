package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class GenericRuntimeTypeAnnotationsAttribute implements Body {

    private byte[] body;

    @Override
    public void read(ByteBuf buf) {
        // TODO Implement on relevance
        body = buf.getRawBuffer();
        buf.setRawBuffer(new byte[0]);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.write(body);
    }
}
