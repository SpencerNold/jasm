package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class SignatureAttribute implements Body {

    private int signatureIndex;

    @Override
    public void read(ByteBuf buf) {
        signatureIndex = buf.readShort();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(signatureIndex);
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }
}
