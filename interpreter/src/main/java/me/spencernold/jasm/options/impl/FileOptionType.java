package me.spencernold.jasm.options.impl;

import me.spencernold.jasm.options.Option;

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
