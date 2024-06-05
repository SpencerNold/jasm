#include "me_spencernold_llvm_binding_LLVM.hpp"

/*
#include "llvm/ADT/APFloat.h"
#include "llvm/ADT/STLExtras.h"
#include "llvm/IR/BasicBlock.h"
#include "llvm/IR/Constants.h"
#include "llvm/IR/DerivedTypes.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/Instructions.h"
#include "llvm/IR/Type.h"
#include "llvm/MC/TargetRegistry.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/TargetParser/Host.h"
*/

#include <iostream>
#include <string>
#include <vector>

#include "jasm.hpp"

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateModule(JNIEnv* env, jobject object, jstring name) {
    jasm::Module* module = new jasm::Module();
    module->init(env->GetStringUTFChars(name, NULL));
    return (jlong) module;
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetThisType(JNIEnv* env, jobject object, jlong address) {
    jasm::Module* module = (jasm::Module*) address;
    return (jlong) module->getThisType();
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetNumericType(JNIEnv* env, jobject object, jlong address, jint index) {
    jasm::Module* module = (jasm::Module*) address;
    return (jlong) module->getNumericType(NumericType(index));
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddStructFields(JNIEnv* env, jobject object, jlong address, jlongArray typeHandles) {
    jsize length = env->GetArrayLength(typeHandles);
    jlong* array = env->GetLongArrayElements(typeHandles, NULL);
    std::vector<llvm::Type*> types;
    for (int i = 0; i < length; i++) {
        types.push_back((llvm::Type*) array[i]);
    }
    jasm::Module* module = (jasm::Module*) address;
    module->setStructBody(types);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateFunction(JNIEnv* env, jobject object, jlong address, jstring name, jint access, jlong typeHandle, jlongArray parameterHandles) {
    jsize length = env->GetArrayLength(parameterHandles);
    jlong* array = env->GetLongArrayElements(parameterHandles, NULL);
    std::vector<llvm::Type*> parameters;
    for (int i = 0; i < length; i++) {
        parameters.push_back((llvm::Type*) array[i]);
    }
    jasm::Module* module = (jasm::Module*) address;
    jasm::Function* function = module->createFunction(env->GetStringUTFChars(name, NULL), (int32_t) access, nullptr, parameters);
    return (jlong) function;
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFunctionBody(JNIEnv* env, jobject object, jlong address) {
    jasm::Function* function = (jasm::Function*) address;
    function->addFunctionBody();
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetParameterReference(JNIEnv* env, jobject object, jlong address, jint index) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->getArgument((uint32_t) index);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetI32Constant(JNIEnv* env, jobject object, jlong address, jint val) {
    jasm::Module* module = (jasm::Module*) address;
    llvm::Value* value = jasm::value::getI32Const(module->builder.get(), (int32_t) val);
    return (jlong) value;
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetI64Constant(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Module* module = (jasm::Module*) address;
    llvm::Value* value = jasm::value::getI64Const(module->builder.get(), (int64_t) val);
    return (jlong) value;
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetF32Constant(JNIEnv* env, jobject object, jlong address, jfloat val) {
    jasm::Module* module = (jasm::Module*) address;
    llvm::Value* value = jasm::value::getF32Const(module->builder.get(), (float) val);
    return (jlong) value;
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetF64Constant(JNIEnv* env, jobject object, jlong address, jdouble val) {
    jasm::Module* module = (jasm::Module*) address;
    llvm::Value* value = jasm::value::getF64Const(module->builder.get(), (double) val);
    return (jlong) value;
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddReturn(JNIEnv* env, jobject object, jlong address, jlong value) {
    jasm::Function* function = (jasm::Function*) address;
    function->addReturn((llvm::Value*) value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAllocate(JNIEnv* env, jobject object, jlong address, jlong type) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->allocate((llvm::Type*) type);
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmStore(JNIEnv* env, jobject object, jlong address, jlong ptr, jlong value) {
    jasm::Function* function = (jasm::Function*) address;
    function->store((llvm::AllocaInst*) ptr, (llvm::Value*) value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmLoad(JNIEnv* env, jobject object, jlong address, jlong type, jlong ptr) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->load((llvm::Type*) type, (llvm::AllocaInst*) ptr);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointAdd(JNIEnv* env, jobject object, jlong address, jlong val1, jlong val2) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->addFloatingPointAdd((llvm::Value*) val1, (llvm::Value*) val2);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointSubtract(JNIEnv* env, jobject object, jlong address, jlong val1, jlong val2) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->addFloatingPointSubtract((llvm::Value*) val1, (llvm::Value*) val2);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointDivide(JNIEnv* env, jobject object, jlong address, jlong val1, jlong val2) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->addFloatingPointDivide((llvm::Value*) val1, (llvm::Value*) val2);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointModulus(JNIEnv* env, jobject object, jlong address, jlong val1, jlong val2) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->addFloatingPointModulus((llvm::Value*) val1, (llvm::Value*) val2);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointMultiply(JNIEnv* env, jobject object, jlong address, jlong val1, jlong val2) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->addFloatingPointMultiply((llvm::Value*) val1, (llvm::Value*) val2);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointNegate(JNIEnv* env, jobject object, jlong address, jlong value) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->addFloatingPointNegate((llvm::Value*) value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddD2FCast(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::Value* value = (llvm::Value*) val;
    return (jlong) function->addDoubleToFloatCast(value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddD2LCast(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::Value* value = (llvm::Value*) val;
    return (jlong) function->addDoubleToLongCast(value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddD2ICast(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::Value* value = (llvm::Value*) val;
    return (jlong) function->addDoubleToIntCast(value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddF2DCast(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::Value* value = (llvm::Value*) val;
    return (jlong) function->addFloatToDoubleCast(value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddF2ICast(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::Value* value = (llvm::Value*) val;
    return (jlong) function->addFloatToIntCast(value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddF2LCast(JNIEnv* env, jobject object, jlong address, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::Value* value = (llvm::Value*) val;
    return (jlong) function->addFloatToLongCast(value);
}

JNIEXPORT jboolean JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFinishFunction(JNIEnv* env, jobject object, jlong address) {
    jasm::Function* function = (jasm::Function*) address;
    return (jboolean) function->finish();
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmWriteModule(JNIEnv* env, jobject object, jlong address) {
    jasm::Module* module = (jasm::Module*) address;
    module->write();
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFreeModule(JNIEnv* env, jobject object, jlong address) {
    jasm::Module* module = (jasm::Module*) address;
    module->free();
    delete(module);
}