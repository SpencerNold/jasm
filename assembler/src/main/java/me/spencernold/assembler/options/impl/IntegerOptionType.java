package me.spencernold.assembler.options.impl;

import me.spencernold.assembler.options.Option;

public class IntegerOptionType extends Option<Integer> {

    public IntegerOptionType(String raw) {
        super(raw);
    }

    @Override
    public Integer read() {
        return Integer.parseInt(raw);
    }
}
