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

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddReturn(JNIEnv* env, jobject object, jlong address, jlong value) {
    jasm::Function* function = (jasm::Function*) address;
    function->addReturn((llvm::Value*) value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAllocateI32(JNIEnv* env, jobject object, jlong address) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->allocateI32();
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAllocateI64(JNIEnv* env, jobject object, jlong address) {
    jasm::Function* function = (jasm::Function*) address;
    return (jlong) function->allocateI64();
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmStoreI32(JNIEnv* env, jobject object, jlong address, jlong ptr, jlong val) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::AllocaInst* pointer = (llvm::AllocaInst*) ptr;
    llvm::Value* value = (llvm::Value*) val;
    function->storeI32(pointer, value);
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmLoadI32(JNIEnv* env, jobject object, jlong address, jlong ptr) {
    jasm::Function* function = (jasm::Function*) address;
    llvm::AllocaInst* pointer = (llvm::AllocaInst*) ptr;
    return (jlong) function->loadI32(pointer);
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFinishFunction(JNIEnv* env, jobject object, jlong address) {
    jasm::Function* function = (jasm::Function*) address;
    bool verify = function->finish();
    std::cout << function->name << ": " << (verify == 0 ? "success" : "error") << std::endl;
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