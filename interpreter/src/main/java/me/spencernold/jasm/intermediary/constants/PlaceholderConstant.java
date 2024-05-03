package me.spencernold.jasm.intermediary.constants;

import me.spencernold.jasm.ByteBuf;

public class PlaceholderConstant extends Constant {

    public PlaceholderConstant() {
        super(-1);
    }

    @Override
    public void write(ByteBuf buf) {
    }
}
