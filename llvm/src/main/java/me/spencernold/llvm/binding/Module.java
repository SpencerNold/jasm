package me.spencernold.llvm.binding;

public final class Module extends LLVM {

    private final long handle;

    public Module(long handle) {
        this.handle = handle;
    }

    public void createFunction(String name, Type type, Type[] parameters) {
        long[] parameterHandles = new long[parameters.length];
        for (int i = 0; i < parameters.length; i++)
            parameterHandles[i] = parameters[i].handle;
        llvmCreateFunction(handle, name, 0x0001, type.handle, parameterHandles);
    }

    public void write() {
        llvmWriteModule(handle);
    }

    public void free() {
        llvmFreeModule(handle);
    }
}
