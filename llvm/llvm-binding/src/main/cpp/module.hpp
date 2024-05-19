#pragma once

#include "llvm/IR/Module.h"
#include "llvm/IR/LLVMContext.h"
#include "llvm/IR/IRBuilder.h"

#include <iostream>
#include <vector>
#include <string>

class JasmModule {
    private:
        std::string name;
        std::unique_ptr<llvm::Module> module;
        std::unique_ptr<llvm::IRBuilder<>> builder;

        llvm::StructType* structType;
        std::vector<llvm::Function*> functions;

        llvm::Function* createClassFunction(std::string, llvm::Type*, std::vector<llvm::Type*>&);
        llvm::Function* createStaticFunction(std::string, llvm::Type*, std::vector<llvm::Type*>&);
    public:
        void init(std::string);
        llvm::Function* createFunction(std::string, int32_t, llvm::Type*, std::vector<llvm::Type*>&);
        bool write();
};