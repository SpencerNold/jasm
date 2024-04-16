package me.spencernold.assembler.options.impl;

import me.spencernold.assembler.options.Option;

public class StringOptionType extends Option<String> {

    public StringOptionType(String raw) {
        super(raw);
    }

    @Override
    public String read() {
        return raw;
    }
}
