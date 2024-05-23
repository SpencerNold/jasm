package me.spencernold.llvm.binding;

public final class Function extends LLVM {

    final long handle;

    public Function(long handle) {
        this.handle = handle;
    }

    public void startBody() {
        llvmAddFunctionBody(handle);
    }

    public Value getArgumentRef(int index) {
        return new Value(llvmGetParameterReference(handle, index));
    }

    public void addReturn(Value value) {
        llvmAddReturn(handle, value.handle);
    }

    public Value allocateI32() {
        return new Value(llvmAllocateI32(handle));
    }

    public Value allocateI64() {
        return new Value(llvmAllocateI64(handle));
    }

    public void storeI32(Value pointer, Value value) {
        llvmStoreI32(handle, pointer.handle, value.handle);
    }

    public Value loadI32(Value pointer) {
        return new Value(llvmLoadI32(handle, pointer.handle));
    }

    public void finish() {
        llvmFinishFunction(handle);
    }
}
