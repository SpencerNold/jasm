package me.spencernold.llvm.binding;

public final class Function extends LLVM {

    final Module owner;
    final long handle;

    public Function(Module module, long handle) {
        this.owner = module;
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

    public Value allocate(NumericType type) {
        return new Value(llvmAllocate(handle, owner.getNumericType(type).handle));
    }

    public void store(Value pointer, Value value) {
        llvmStore(handle, pointer.handle, value.handle);
    }

    public Value load(NumericType type, Value pointer) {
        return new Value(llvmLoad(handle, owner.getNumericType(type).handle, pointer.handle));
    }

    public Value addFloatingPointTypes(Value d1, Value d2) {
        return new Value(llvmAddFloatingPointAdd(handle, d1.handle, d2.handle));
    }

    public Value subtractFloatingPointTypes(Value d1, Value d2) {
        return new Value(llvmAddFloatingPointSubtract(handle, d1.handle, d2.handle));
    }

    public Value divideFloatingPointTypes(Value d1, Value d2) {
        return new Value(llvmAddFloatingPointDivide(handle, d1.handle, d2.handle));
    }

    public Value modulusFloatingPointTypes(Value d1, Value d2) {
        return new Value(llvmAddFloatingPointModulus(handle, d1.handle, d2.handle));
    }

    public Value multiplyFloatingPointTypes(Value d1, Value d2) {
        return new Value(llvmAddFloatingPointMultiply(handle, d1.handle, d2.handle));
    }

    public Value negateFloatingPointType(Value d) {
        return new Value(llvmAddFloatingPointNegate(handle, d.handle));
    }

    public Value castD2F(Value value) {
        return new Value(llvmAddD2FCast(handle, value.handle));
    }

    public Value castF2D(Value value) {
        return new Value(llvmAddF2DCast(handle, value.handle));
    }

    public Value castF2I(Value value) {
        return new Value(llvmAddF2ICast(handle, value.handle));
    }

    public Value castF2L(Value value) {
        return new Value(llvmAddF2LCast(handle, value.handle));
    }

    public Value castD2L(Value value) {
        return new Value(llvmAddD2LCast(handle, value.handle));
    }

    public Value castD2I(Value value) {
        return new Value(llvmAddD2ICast(handle, value.handle));
    }

    public boolean finish() {
        return llvmFinishFunction(handle);
    }
}
