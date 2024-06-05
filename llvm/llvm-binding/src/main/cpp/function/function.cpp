#include "../jasm.hpp"

#include "llvm/IR/Verifier.h"

extern llvm::LLVMContext* context;

void jasm::Function::addFunctionBody() {
    llvm::BasicBlock* block = llvm::BasicBlock::Create(*context, "entry", function);
    module->builder->SetInsertPoint(block);
}

void jasm::Function::addReturn(llvm::Value* value) {
    module->builder->CreateRet(value);
}

bool jasm::Function::finish() {
    return llvm::verifyFunction(*function);
}