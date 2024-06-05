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
 * Method:    llvmGetF32Constant
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetF32Constant(JNIEnv*, jobject, jlong, jfloat);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmGetF64Constant
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmGetF64Constant(JNIEnv*, jobject, jlong, jdouble);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddReturn
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddReturn(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAllocate
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAllocate(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmStore
 * Signature: (JJJ)V
 */
JNIEXPORT void JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmStore(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmLoad
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmLoad(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFloatingPointAdd
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointAdd(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFloatingPointSubtract
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointSubtract(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFloatingPointDivide
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointDivide(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFloatingPointModulus
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointModulus(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFloatingPointMultiply
 * Signature: (JJJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointMultiply(JNIEnv*, jobject, jlong, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddFloatingPointNegate
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddFloatingPointNegate(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddD2FCast
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddD2FCast(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddF2DCast
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddF2DCast(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddF2ICast
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddF2ICast(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddF2LCast
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddF2LCast(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddD2LCast
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddD2LCast(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmAddD2ICast
 * Signature: (JJ)J
 */
JNIEXPORT jlong JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmAddD2ICast(JNIEnv*, jobject, jlong, jlong);

/*
 * Class:     me_spencernold_llvm_binding_LLVM
 * Method:    llvmFinishFunction
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_me_spencernold_llvm_binding_LLVM_llvmFinishFunction(JNIEnv*, jobject, jlong);

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
