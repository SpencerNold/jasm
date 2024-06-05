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

    // jasm::Module* (String)
    protected native long llvmCreateModule(String name);

    // void (jasm::Module*, llvm::Type*[])
    protected native void llvmAddStructFields(long handle, long[] typeHandles);

    // llvm::Type* (jasm::Module*)
    protected native long llvmGetThisType(long handle);

    // llvm::Type* (jasm::Module*, int)
    protected native long llvmGetNumericType(long handle, int index);

    // jasm::Function* (jasm::Module*, String, int, llvm::Type*, llvm::Type*[])
    protected native long llvmCreateFunction(long handle, String name, int access, long typeHandle, long[] parameterHandles);

    // void (jasm::Function*)
    protected native void llvmAddFunctionBody(long handle);

    // void (jasm::Function*)
    protected native boolean llvmFinishFunction(long handle);

    // llvm::Value* (jasm::Function*, int)
    protected native long llvmGetParameterReference(long handle, int value);

    // llvm::Value* (jasm::Module*, int)
    protected native long llvmGetI32Constant(long handle, int value);

    // llvm::Value* (jasm::Module*, long)
    protected native long llvmGetI64Constant(long handle, long value);

    // llvm::Value* (jasm::Module*, float)
    protected native long llvmGetF32Constant(long handle, float value);

    // llvm::Value* (jasm::Module*, double)
    protected native long llvmGetF64Constant(long handle, double value);

    // void (jasm::Function*, llvm::Value*)
    protected native void llvmAddReturn(long handle, long value);

    // llvm::AllocaInst* (jasm::Function*, llvm::Type*)
    protected native long llvmAllocate(long handle, long type);

    // void (jasm::Function*, llvm::AllocaInst*, llvm::Value*)
    protected native void llvmStore(long handle, long ptr, long value);

    // llvm::Value* (jasm::Function*, llvm::AllocaInst*)
    protected native long llvmLoad(long handle, long type, long ptr);

    // llvm::Value* (jasm::Function*, llvm::Value*, llvm::Value*)
    protected native long llvmAddFloatingPointAdd(long handle, long value1, long value2);
    // llvm::Value* (jasm::Function*, llvm::Value*, llvm::Value*)
    protected native long llvmAddFloatingPointSubtract(long handle, long value1, long value2);

    // llvm::Value* (jasm::Function*, llvm::Value*, llvm::Value*)
    protected native long llvmAddFloatingPointDivide(long handle, long value1, long value2);

    // llvm::Value* (jasm::Function*, llvm::Value*, llvm::Value*)
    protected native long llvmAddFloatingPointModulus(long handle, long value1, long value2);

    // llvm::Value* (jasm::Function*, llvm::Value*, llvm::Value*)
    protected native long llvmAddFloatingPointMultiply(long handle, long value1, long value2);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddFloatingPointNegate(long handle, long value);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddD2FCast(long handle, long value);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddD2LCast(long handle, long value);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddD2ICast(long handle, long value);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddF2DCast(long handle, long value);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddF2ICast(long handle, long value);

    // llvm::Value* (jasm::Function*, llvm::Value*)
    protected native long llvmAddF2LCast(long handle, long value);

    // void (jasm::Module*)
    protected native void llvmWriteModule(long handle);

    // void (jasm::Module*)
    protected native void llvmFreeModule(long handle);
}
