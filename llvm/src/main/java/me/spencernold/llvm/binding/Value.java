package me.spencernold.llvm.binding;

public final class Value extends LLVM {

    final long handle;

    public Value(long handle) {
        this.handle = handle;
    }
}
