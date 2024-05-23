package me.spencernold.llvm.binding.operations;

import me.spencernold.llvm.binding.Module;
import me.spencernold.llvm.binding.Operation;
import me.spencernold.llvm.binding.Value;

public class IntegralConstant implements Operation {

    private final int value;

    public IntegralConstant(int value) {
        this.value = value;
    }


    @Override
    public Value getAsValue(Module module) {
        return module.getI32Constant(value);
    }
}
