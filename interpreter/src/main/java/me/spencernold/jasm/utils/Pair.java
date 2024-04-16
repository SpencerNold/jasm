package me.spencernold.jasm.utils;

public class Pair<A, B> {

    public A key;
    public B value;

    public Pair(A key, B value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {}

    public A getKey() {
        return key;
    }

    public void setKey(A key) {
        this.key = key;
    }

    public B getValue() {
        return value;
    }

    public void setValue(B value) {
        this.value = value;
    }
}
