#include "module.hpp"

#include "llvm/IR/LegacyPassManager.h"
#include "llvm/MC/TargetRegistry.h"
#include "llvm/Target/TargetMachine.h"
#include "llvm/Target/TargetOptions.h"
#include "llvm/TargetParser/Host.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/TargetSelect.h"

#define ACC_STATIC 0x0008

llvm::LLVMContext* context = nullptr;

llvm::Function* JasmModule::createClassFunction(std::string name, llvm::Type* type, std::vector<llvm::Type*> &parameters) {
    parameters.insert(parameters.begin(), llvm::PointerType::get(structType, 0));
    return createStaticFunction(name, type, parameters);
}

llvm::Function* JasmModule::createStaticFunction(std::string name, llvm::Type* type, std::vector<llvm::Type*> &parameters) {
    llvm::Function* function = module->getFunction(name);
    if (function == nullptr) {
        llvm::FunctionType* functionType = llvm::FunctionType::get(type, parameters, false);
        function = llvm::Function::Create(functionType, llvm::Function::ExternalLinkage, name, *module);
    }
    return function;
}

void JasmModule::init(std::string name) {
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

llvm::Function* JasmModule::createFunction(std::string name, int32_t access, llvm::Type* type, std::vector<llvm::Type*> &parameters) {
    llvm::Function* function;
    if ((access & ACC_STATIC) == ACC_STATIC)
        function = createStaticFunction(name, type, parameters);
    else
        function = createClassFunction(name, type, parameters);
    functions.push_back(function);
    return function;
}

bool JasmModule::write() {
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