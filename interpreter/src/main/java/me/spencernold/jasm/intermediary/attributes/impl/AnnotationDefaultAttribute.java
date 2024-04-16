package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.annotations.ElementValue;

public class AnnotationDefaultAttribute implements Body {

    private final ElementValue defaultValue;

    public AnnotationDefaultAttribute() {
        defaultValue = new ElementValue();
    }

    @Override
    public void read(ByteBuf buf) {
        defaultValue.read(buf);
    }

    @Override
    public void write(ByteBuf buf) {
        defaultValue.write(buf);
    }
}
