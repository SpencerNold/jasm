package me.spencernold.assembler.writer;

public interface InputJWritable<K, V> {
    void write(K k, V v);
}
