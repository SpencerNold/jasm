package me.spencernold.llvm.binding;

public interface Operation {
    Value getAsValue(Module module);
}
