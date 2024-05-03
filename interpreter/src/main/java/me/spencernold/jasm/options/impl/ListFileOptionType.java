package me.spencernold.jasm.options.impl;

import me.spencernold.jasm.options.Option;

import java.io.File;

public class ListFileOptionType extends Option<File[]> {

    public ListFileOptionType(String raw) {
        super(raw);
    }

    @Override
    public File[] read() {
        String[] paths = raw.split(File.pathSeparator);
        File[] files = new File[paths.length];
        for (int i = 0; i < files.length; i++)
            files[i] = new FileOptionType(paths[i]).read();
        return files;
    }
}
