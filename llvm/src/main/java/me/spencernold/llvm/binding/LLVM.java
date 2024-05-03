package me.spencernold.llvm.binding;

public class LLVM {

    static {
        System.loadLibrary("llvm");
    }

    public native void print();
}
