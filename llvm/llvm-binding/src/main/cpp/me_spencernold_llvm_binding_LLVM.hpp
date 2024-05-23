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
 * Method:    llvmGetThisType
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetThisType(JNIEnv*, jobject, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmGetNumericType
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetNumericType(JNIEnv*, jobject, jlong, jint);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddStructFields
 * Signature: (J[J])V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddStructFields(JNIEnv*, jobject, jlong, jlongArray);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmCreateFunction
 * Signature: (JLjava/lang/String;IJ[J)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmCreateFunction(JNIEnv*, jobject, jlong, jstring, jint, jlong, jlongArray);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFunctionBody
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFunctionBody(JNIEnv*, jobject, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmGetParameterReference
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetParameterReference(JNIEnv*, jobject, jlong, jint);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmGetI32Constant
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetI32Constant(JNIEnv*, jobject, jlong, jint);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmGetI64Constant
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetI64Constant(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddReturn
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddReturn(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAllocateI32
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAllocateI32(JNIEnv*, jobject, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAllocateI64
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAllocateI64(JNIEnv*, jobject, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmStoreI32
 * Signature: (JJJ)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmStoreI32(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmLoadI32
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmLoadI32(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmFinishFunction
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFinishFunction(JNIEnv*, jobject, jlong);

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
