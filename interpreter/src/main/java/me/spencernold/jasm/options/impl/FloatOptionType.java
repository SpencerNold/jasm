package me.spencernold.jasm.options.impl;

import me.spencernold.jasm.options.Option;

public class FloatOptionType extends Option<Float> {

    public FloatOptionType(String raw) {
        super(raw);
    }

    @Override
    public Float read() {
        return Float.parseFloat(raw);
    }
}
