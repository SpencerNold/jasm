package me.spencernold.jasm.options.impl;

import me.spencernold.jasm.options.Option;

public class StringOptionType extends Option<String> {

    public StringOptionType(String raw) {
        super(raw);
    }

    @Override
    public String read() {
        return raw;
    }
}
