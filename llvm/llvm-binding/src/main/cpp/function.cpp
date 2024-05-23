#include "jasm.hpp"

#include "llvm/IR/Verifier.h"

extern llvm::LLVMContext* context;

void jasm::Function::addFunctionBody() {
    llvm::BasicBlock* block = llvm::BasicBlock::Create(*context, "entry", function);
    module->builder->SetInsertPoint(block);
}

llvm::Value* jasm::Function::getArgument(uint32_t index) {
    return function->getArg(index);
}

llvm::AllocaInst* jasm::Function::allocateI32() {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateAlloca(builder->getInt32Ty());
}

llvm::AllocaInst* jasm::Function::allocateI64() {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateAlloca(builder->getInt64Ty());
}

void jasm::Function::storeI32(llvm::AllocaInst* ptr, llvm::Value* value) {
    llvm::IRBuilder<>* builder = module->builder.get();
    builder->CreateStore(value, ptr);
}

llvm::LoadInst* jasm::Function::loadI32(llvm::AllocaInst* ptr) {
    llvm::IRBuilder<>* builder = module->builder.get();
    return builder->CreateLoad(builder->getInt32Ty(), ptr);
}

void jasm::Function::addReturn(llvm::Value* value) {
    module->builder->CreateRet(value);
}

bool jasm::Function::finish() {
    return llvm::verifyFunction(*function);
}