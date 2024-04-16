package me.spencernold.assembler.options.impl;

import me.spencernold.assembler.options.Option;

import java.io.File;

public class FileOptionType extends Option<File> {

    public FileOptionType(String raw) {
        super(raw);
    }

    @Override
    public File read() {
        // TODO Add more parsing options
        return new File(raw);
    }
}
