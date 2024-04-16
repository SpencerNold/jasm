package me.spencernold.assembler.options.impl;

import me.spencernold.assembler.options.Option;

public class FloatOptionType extends Option<Float> {

    public FloatOptionType(String raw) {
        super(raw);
    }

    @Override
    public Float read() {
        return Float.parseFloat(raw);
    }
}
