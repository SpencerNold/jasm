#include "../jasm.hpp"

llvm::Value* jasm::Function::getArgument(uint32_t index) {
    return function->getArg(index);
}

llvm::AllocaInst* jasm::Function::allocate(llvm::Type* type) {
    return module->builder->CreateAlloca(type);
}

void jasm::Function::store(llvm::AllocaInst* ptr, llvm::Value* value) {
    module->builder->CreateStore(value, ptr);
}

llvm::LoadInst* jasm::Function::load(llvm::Type* type, llvm::AllocaInst* ptr) {
    module->builder->CreateLoad(type, ptr);
}