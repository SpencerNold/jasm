package me.spencernold.llvm.binding;

public final class Module extends LLVM {

    final long handle;

    public Module(long handle) {
        this.handle = handle;
    }

    public Type getModuleType() {
        return new Type(llvmGetThisType(handle));
    }

    public Type getNumericType(NumericType type) {
        return new Type(llvmGetNumericType(handle, type.ordinal()));
    }

    public void setFields(Type[] types) {
        long[] typeHandles = new long[types.length];
        for (int i = 0; i < types.length; i++)
            typeHandles[i] = types[i].handle;
        llvmAddStructFields(handle, typeHandles);
    }

    public Function createFunction(String name, int access, Type type, Type[] parameters) {
        long[] parameterHandles = new long[parameters.length];
        for (int i = 0; i < parameters.length; i++)
            parameterHandles[i] = parameters[i].handle;
        long functionHandle = llvmCreateFunction(handle, name, access, type.handle, parameterHandles);
        return new Function(functionHandle);
    }

    public Value getI32Constant(int value) {
        return new Value(llvmGetI32Constant(handle, value));
    }

    public Value getI64Constant(long value) {
        return new Value(llvmGetI64Constant(handle, value));
    }

    public Value getNullValue() {
        return new Value(0L);
    }

    public void write() {
        llvmWriteModule(handle);
    }

    public void free() {
        llvmFreeModule(handle);
    }
}
