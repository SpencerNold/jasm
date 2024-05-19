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
#include "llvm/IR/Verifier.h"
#include "llvm/MC/TargetRegistry.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/TargetParser/Host.h"
*/

#include <iostream>
#include <string>
#include <vector>

#include "module.hpp"

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateModule(JNIEnv* env, jobject object, jstring name) {
    JasmModule* llvmModule = new JasmModule();
    llvmModule->init(env->GetStringUTFChars(name, NULL));
    return (jlong) llvmModule;
}

JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateFunction(JNIEnv* env, jobject object, jlong address, jstring name, jint access, jlong typeHandle, jlongArray parameterHandles) {
    JasmModule* llvmModule = (JasmModule*) address;
    jsize length = env->GetArrayLength(parameterHandles);
    jlong* array = env->GetLongArrayElements(parameterHandles, NULL);
    std::vector<llvm::Type*> parameters(length);
    for (int i = 0; i < length; i++)
        parameters.push_back((llvm::Type*) array[i]);
    llvm::Function* function = llvmModule->createFunction(env->GetStringUTFChars(name, NULL), (int32_t) access, (llvm::Type*) typeHandle, parameters);
    return (jlong) function;
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmWriteModule(JNIEnv* env, jobject object, jlong address) {
    JasmModule* llvmModule = (JasmModule*) address;
    llvmModule->write();
}

JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFreeModule(JNIEnv* env, jobject object, jlong address) {
    delete((JasmModule*) address);
}