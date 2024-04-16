package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;

public class SyntheticAttribute implements Body {
    @Override
    public void read(ByteBuf buf) {
        // The synthetic attribute has no body
    }

    @Override
    public void write(ByteBuf buf) {
        // The synthetic attribute has no body
    }
}
