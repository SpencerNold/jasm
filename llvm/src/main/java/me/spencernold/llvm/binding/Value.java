package me.spencernold.llvm.binding;

public final class Value extends LLVM {

    public static Value NULL = new Value(0L);

    public static int TYPE_2 = 1;

    final long handle;
    int flags;

    public Value(long handle) {
        this(handle, 0);
    }

    public Value(long handle, int flags) {
        this.handle = handle;
        this.flags = flags;
    }

    public boolean isType2() {
        return (flags & TYPE_2) == TYPE_2;
    }

    public Value setType2(boolean type2) {
        if (type2)
            flags = flags | TYPE_2;
        else
            flags = flags & (~TYPE_2);
        return this;
    }
}
