package me.spencernold.jasm.options;

public abstract class Option<T> {

    protected final String raw;

    public Option(String raw) {
        this.raw = raw;
    }

    public abstract T read();
}
