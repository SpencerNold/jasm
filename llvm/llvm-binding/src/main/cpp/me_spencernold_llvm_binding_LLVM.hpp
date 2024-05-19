#include <jni.h>

#ifndef _Included_me_spencernold_llvm_binding_LLVM
#define _Included_me_spencernold_llvm_binding_LLVM
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmCreateModule
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateModule(JNIEnv*, jobject, jstring);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmCreateFunction
 * Signature: (JLjava/lang/String;ILme/spencernold/llvm/binding/Type;[Lme/spencernold/llvm/binding/Type;)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateFunction(JNIEnv*, jobject, jlong, jstring, jint, jlong, jlongArray);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmWriteModule
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmWriteModule(JNIEnv*, jobject, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmFreeModule
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFreeModule(JNIEnv*, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
