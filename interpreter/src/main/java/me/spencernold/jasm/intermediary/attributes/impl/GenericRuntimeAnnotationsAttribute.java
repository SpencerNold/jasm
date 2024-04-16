package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.annotations.Annotation;

public abstract class GenericRuntimeAnnotationsAttribute implements Body {

    private Annotation[] annotations;

    @Override
    public void read(ByteBuf buf) {
        int length = buf.readShort();
        annotations = new Annotation[length];
        for (int i = 0; i < length; i++) {
            Annotation annotation = new Annotation();
            annotation.read(buf);
            annotations[i] = annotation;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(annotations.length);
        for (Annotation annotation : annotations)
            annotation.write(buf);
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }
}
