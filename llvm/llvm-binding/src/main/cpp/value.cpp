#include "jasm.hpp"

extern llvm::LLVMContext* context;

llvm::Value* jasm::value::getI32Const(llvm::IRBuilder<>* builder, int32_t value) {
    return llvm::ConstantInt::get(builder->getInt32Ty(), value, true);
}

llvm::Value* jasm::value::getI64Const(llvm::IRBuilder<>* builder, int64_t value) {
    return llvm::ConstantInt::get(builder->getInt64Ty(), value, true);
}

llvm::Value* jasm::value::getF32Const(llvm::IRBuilder<>* builder, float value) {
    return llvm::ConstantFP::get(builder->getFloatTy(), value);
}

llvm::Value* jasm::value::getF64Const(llvm::IRBuilder<>* builder, double value) {
    return llvm::ConstantFP::get(builder->getDoubleTy(), value);
}