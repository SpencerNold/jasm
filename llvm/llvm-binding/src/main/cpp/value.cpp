#include "jasm.hpp"

extern llvm::LLVMContext* context;

llvm::Value* jasm::value::getI32Const(llvm::IRBuilder<>* builder, int32_t value) {
    return llvm::ConstantInt::get(builder->getInt32Ty(), value, true);
}

llvm::Value* jasm::value::getI64Const(llvm::IRBuilder<>* builder, int64_t value) {
    return llvm::ConstantInt::get(builder->getInt64Ty(), value, true);
}