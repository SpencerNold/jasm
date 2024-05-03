package me.spencernold.jasm.options.impl;

import me.spencernold.jasm.options.Option;

public class IntegerOptionType extends Option<Integer> {

    public IntegerOptionType(String raw) {
        super(raw);
    }

    @Override
    public Integer read() {
        return Integer.parseInt(raw);
    }
}
