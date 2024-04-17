package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.ReadWriteable;
import me.spencernold.jasm.intermediary.attributes.Body;

public class MethodParametersAttribute implements Body {

    private Parameter[] parameters;

    @Override
    public void read(ByteBuf buf) {
        int count = buf.readByte();
        parameters = new Parameter[count];
        for (int i = 0; i < count; i++) {
            Parameter parameter = new Parameter();
            parameter.read(buf);
            parameters[i] = parameter;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeByte(parameters.length);
        for (Parameter parameter : parameters)
            parameter.write(buf);
    }

    private static class Parameter implements ReadWriteable<ByteBuf> {

        private int name, access;

        @Override
        public void read(ByteBuf buf) {
            name = buf.readShort();
            access = buf.readShort();
        }

        @Override
        public void write(ByteBuf buf) {
            buf.writeShort(name);
            buf.writeShort(access);
        }

        public int getName() {
            return name;
        }

        public int getAccess() {
            return access;
        }
    }
}
