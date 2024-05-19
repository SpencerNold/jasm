package me.spencernold.llvm.binding;

public class LLVM {

    static {
        System.loadLibrary("llvm-binding");
    }

    public static final LLVM INSTANCE = new LLVM();

    public Module createModule(String name) {
        long handle = llvmCreateModule(name);
        return new Module(handle);
    }

    protected native long llvmCreateModule(String name);
    protected native long llvmCreateFunction(long handle, String name, int access, long typeHandle, long[] parameterHandles);
    protected native void llvmWriteModule(long handle);
    protected native void llvmFreeModule(long handle);
}
