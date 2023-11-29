package me.spencernold.assembler.parser;

import me.spencernold.assembler.ObjectWriter;
import me.spencernold.jasm.intermediary.JReadable;
import me.spencernold.jasm.intermediary.JWritable;

public interface ParserWriter<T> extends JReadable<T>, JWritable<ObjectWriter> {
}
