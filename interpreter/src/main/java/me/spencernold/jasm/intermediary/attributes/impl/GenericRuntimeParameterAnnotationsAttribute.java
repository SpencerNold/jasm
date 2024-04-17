package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.annotations.ParameterAnnotation;

public abstract class GenericRuntimeParameterAnnotationsAttribute implements Body {

    private ParameterAnnotation[] annotations;

    @Override
    public void read(ByteBuf buf) {
        int length = buf.readByte();
        annotations = new ParameterAnnotation[length];
        for (int i = 0; i < length; i++) {
            ParameterAnnotation annotation = new ParameterAnnotation();
            annotation.read(buf);
            annotations[i] = annotation;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(annotations.length);
        for (ParameterAnnotation annotation : annotations)
            annotation.write(buf);
    }
}
