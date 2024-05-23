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
    protected native void llvmFinishFunction(long handle);

    // llvm::Value* (jasm::Function*, int)
    protected native long llvmGetParameterReference(long handle, int value);
    // llvm::Value* (jasm::Module*, int)
    protected native long llvmGetI32Constant(long handle, int value);
    // llvm::Value* (jasm::Module*, long)
    protected native long llvmGetI64Constant(long handle, long value);

    // void (jasm::Function*, llvm::Value*)
    protected native void llvmAddReturn(long handle, long value);

    // llvm::AllocaInst* (jasm::Function*)
    protected native long llvmAllocateI32(long handle);
    // llvm::AllocaInst* (jasm::Function*)
    protected native long llvmAllocateI64(long handle);
    // void (jasm::Function*, llvm::AllocaInst*, llvm::Value*)
    protected native void llvmStoreI32(long handle, long ptr, long value);
    // llvm::Value* (jasm::Function*, llvm::AllocaInst*)
    protected native long llvmLoadI32(long handle, long ptr);

    // void (jasm::Module*)
    protected native void llvmWriteModule(long handle);
    // void (jasm::Module*)
    protected native void llvmFreeModule(long handle);
}
