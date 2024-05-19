package me.spencernold.llvm.binding;

public abstract class Type extends LLVM {

    protected long handle;

    public Type(long handle) {
        this.handle = handle;
    }
}
