#include "jasm.hpp"

#include "llvm/IR/LegacyPassManager.h"
#include "llvm/MC/TargetRegistry.h"
#include "llvm/Target/TargetMachine.h"
#include "llvm/Target/TargetOptions.h"
#include "llvm/TargetParser/Host.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/TargetSelect.h"

llvm::LLVMContext* context = nullptr;

llvm::Function* jasm::Module::createClassFunction(std::string name, bool accessible, llvm::Type* type, std::vector<llvm::Type*> &parameters) {
    parameters.insert(parameters.begin(), llvm::PointerType::get(structType, 0));
    return createStaticFunction(name, accessible, type, parameters);
}

llvm::Function* jasm::Module::createStaticFunction(std::string name, bool accessible, llvm::Type* type, std::vector<llvm::Type*> &parameters) {
    type = type == nullptr ? llvm::Type::getVoidTy(*context) : type;
    llvm::FunctionType* functionType = llvm::FunctionType::get(type, parameters, false);
    return llvm::Function::Create(functionType, accessible ? llvm::Function::ExternalLinkage : llvm::Function::PrivateLinkage, name, *module);

}

void jasm::Module::init(std::string name) {
    static llvm::LLVMContext ctx;
    if (context == nullptr) {
        context = &ctx;
    }
    this->name = name;
    module = std::make_unique<llvm::Module>(name, *context);
    builder = std::make_unique<llvm::IRBuilder<>>(*context);

    // Class wide struct instance
    structType = llvm::StructType::create(*context, name);
}

llvm::StructType* jasm::Module::getThisType() {
    return structType;
}

llvm::Type* jasm::Module::getNumericType(NumericType type) {
    switch (type) {
        case j_boolean_t:
            return builder->getInt1Ty();
        case j_byte_t:
            return builder->getInt8Ty();
        case j_char_t:
        case j_short_t:
            return builder->getInt16Ty();
        case j_int_t:
            return builder->getInt32Ty();
        case j_long_t:
            return builder->getInt64Ty();
        case j_float_t:
            return builder->getFloatTy();
        case j_double_t:
            return builder->getDoubleTy();
        case j_pointer_t:
            return builder->getPtrTy(0);
    }
    return nullptr;
}

void jasm::Module::setStructBody(std::vector<llvm::Type*> types) {
    structType->setBody(types);
}

jasm::Function* jasm::Module::createFunction(std::string name, int32_t access, llvm::Type* type, std::vector<llvm::Type*> &parameters) {
    bool accessible = (access & 0x0001) == 0x0001; // public opcode
    llvm::Function* function;
    if ((access & 0x0008) == 0x0008) // static opcode
        function = createStaticFunction(name, accessible, type, parameters);
    else
        function = createClassFunction(name, accessible, type, parameters);
    jasm::Function* jasmFunction = new jasm::Function(name, this, function);
    functions.push_back(jasmFunction);
    return jasmFunction;
}

bool jasm::Module::write() {
    /* Write to LLVM IR for debugging purposes */
    std::error_code errorCode;
    llvm::raw_fd_ostream outStream(name + ".ll", errorCode);
    module->print(outStream, nullptr);
    /* --------------------------------------- */

    llvm::InitializeAllTargetInfos();
    llvm::InitializeAllTargets();
    llvm::InitializeAllTargetMCs();
    llvm::InitializeAllAsmParsers();
    llvm::InitializeAllAsmPrinters();

    std::string triple = llvm::sys::getDefaultTargetTriple();
    module->setTargetTriple(triple);

    std::string error;
    const llvm::Target* target = llvm::TargetRegistry::lookupTarget(triple, error);
    if (target == nullptr) {
        llvm::errs() << error;
        return false;
    }
    llvm::TargetOptions options;
    llvm::TargetMachine* machine = target->createTargetMachine(triple, "generic", "", options, llvm::Reloc::PIC_);
    module->setDataLayout(machine->createDataLayout());

    std::error_code err;
    llvm::raw_fd_ostream out(name + ".o", err, llvm::sys::fs::OF_None);
    if (err) {
        llvm::errs() << "Could not open file: " << err.message();
            return false;
    }
    llvm::legacy::PassManager pass;
    if (machine->addPassesToEmitFile(pass, out, nullptr, llvm::CodeGenFileType::CGFT_ObjectFile)) {
        llvm::errs() << "Target machine was unnable to write object file";
        return false;
    }
    pass.run(*module);
    out.flush();
    return true;
}

void jasm::Module::free() {
    for (auto func : functions)
        delete(func);
}