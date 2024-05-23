#pragma once

#include "llvm/IR/Module.h"
#include "llvm/IR/LLVMContext.h"
#include "llvm/IR/IRBuilder.h"

#include <iostream>
#include <vector>
#include <string>

enum NumericType {
    // booleans, bytes, chars, shorts do not exist in Java, but they will now
    j_boolean_t, j_byte_t, j_char_t, j_short_t, j_int_t, j_long_t, j_float_t, j_double_t, j_pointer_t
};

namespace jasm {
    class Function;

    class Module {
        private:
            std::string name;
            llvm::StructType* structType;
            std::vector<Function*> functions;

            llvm::Function* createClassFunction(std::string, bool, llvm::Type*, std::vector<llvm::Type*>&);
            llvm::Function* createStaticFunction(std::string, bool, llvm::Type*, std::vector<llvm::Type*>&);
        public:
            std::unique_ptr<llvm::Module> module;
            std::unique_ptr<llvm::IRBuilder<>> builder;

            void init(std::string);
            llvm::StructType* getThisType();
            llvm::Type* getNumericType(NumericType);
            void setStructBody(std::vector<llvm::Type*>);
            Function* createFunction(std::string, int32_t, llvm::Type*, std::vector<llvm::Type*>&);
            bool write();

            void free();
    };

    class Function {
        private:
            Module* module;
            llvm::Function* function;
        public:
            std::string name;

            Function(std::string name, Module* module, llvm::Function* function) {
                this->name = name;
                this->module = module;
                this->function = function;
            }

            void addFunctionBody();

            llvm::Value* getArgument(uint32_t);
            llvm::AllocaInst* allocateI32();
            llvm::AllocaInst* allocateI64();
            void storeI32(llvm::AllocaInst*, llvm::Value*);
            llvm::LoadInst* loadI32(llvm::AllocaInst*);

            void addReturn(llvm::Value*);

            bool finish();
    };

    namespace value {
        llvm::Value* getI32Const(llvm::IRBuilder<>*, int32_t);
        llvm::Value* getI64Const(llvm::IRBuilder<>*, int64_t);
    }
}