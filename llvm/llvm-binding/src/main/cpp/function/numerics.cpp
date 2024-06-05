#include "../jasm.hpp"

llvm::Value* jasm::Function::addFloatingPointAdd(llvm::Value* val1, llvm::Value* val2) {
    return module->builder->CreateFAdd(val1, val2);
}

llvm::Value* jasm::Function::addFloatingPointSubtract(llvm::Value* val1, llvm::Value* val2) {
    return module->builder->CreateFSub(val1, val2);
}

llvm::Value* jasm::Function::addFloatingPointDivide(llvm::Value* val1, llvm::Value* val2) {
    return module->builder->CreateFDiv(val1, val2);
}

llvm::Value* jasm::Function::addFloatingPointModulus(llvm::Value* val1, llvm::Value* val2) {
    return module->builder->CreateFRem(val1, val2);
}

llvm::Value* jasm::Function::addFloatingPointMultiply(llvm::Value* val1, llvm::Value* val2) {
    module->builder->CreateFMul(val1, val2);
}

llvm::Value* jasm::Function::addFloatingPointNegate(llvm::Value* value) {
    module->builder->CreateFNeg(value);
}

llvm::Value* jasm::Function::addDoubleToFloatCast(llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateFPTrunc(value, builder->getFloatTy());
}

llvm::Value* jasm::Function::addDoubleToLongCast(llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateFPToSI(value, builder->getInt64Ty());
}

llvm::Value* jasm::Function::addDoubleToIntCast(llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateFPToSI(value, builder->getInt32Ty());
}


llvm::Value* jasm::Function::addFloatToDoubleCast(llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateFPTrunc(value, builder->getDoubleTy());
}

llvm::Value* jasm::Function::addFloatToIntCast(llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateFPToSI(value, builder->getInt32Ty());
}

llvm::Value* jasm::Function::addFloatToLongCast(llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateFPToSI(value, builder->getInt64Ty());
}