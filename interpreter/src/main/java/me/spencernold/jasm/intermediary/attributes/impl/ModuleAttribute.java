package me.spencernold.jasm.intermediary.attributes.impl;

import me.spencernold.jasm.ByteBuf;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.module.Exports;
import me.spencernold.jasm.intermediary.attributes.impl.module.Opens;
import me.spencernold.jasm.intermediary.attributes.impl.module.Provides;
import me.spencernold.jasm.intermediary.attributes.impl.module.Requires;

public class ModuleAttribute implements Body {

    private int name;
    private int flags;
    private int version;
    private Requires[] requires;
    private Exports[] exports;
    private Opens[] opens;
    private short[] uses;
    private Provides[] provides;

    @Override
    public void read(ByteBuf buf) {
        name = buf.readShort();
        flags = buf.readShort();
        version = buf.readShort();
        requires = new Requires[buf.readShort()];
        for (int i = 0; i < requires.length; i++) {
            Requires req = new Requires();
            req.read(buf);
            requires[i] = req;
        }
        exports = new Exports[buf.readShort()];
        for (int i = 0; i < exports.length; i++) {
            Exports exp = new Exports();
            exp.read(buf);
            exports[i] = exp;
        }
        opens = new Opens[buf.readShort()];
        for (int i = 0; i < opens.length; i++) {
            Opens opn = new Opens();
            opn.read(buf);
            opens[i] = opn;
        }
        uses = new short[buf.readShort()];
        for (int i = 0; i < uses.length; i++)
            uses[i] = (short) buf.readShort();
        provides = new Provides[buf.readShort()];
        for (int i = 0; i < provides.length; i++) {
            Provides pvd = new Provides();
            pvd.read(buf);
            provides[i] = pvd;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeShort(name);
        buf.writeShort(flags);
        buf.writeShort(version);
        buf.writeShort(requires.length);
        for (Requires req : requires)
            req.write(buf);
        buf.writeShort(exports.length);
        for (Exports exp : exports)
            exp.write(buf);
        buf.writeShort(opens.length);
        for (Opens opn : opens)
            opn.write(buf);
        buf.writeShort(uses.length);
        for (short s : uses)
            buf.writeShort(s);
        buf.writeShort(provides.length);
        for (Provides pvd : provides)
            pvd.write(buf);
    }

    public int getName() {
        return name;
    }

    public int getFlags() {
        return flags;
    }

    public int getVersion() {
        return version;
    }

    public Requires[] getRequires() {
        return requires;
    }

    public Exports[] getExports() {
        return exports;
    }

    public Opens[] getOpens() {
        return opens;
    }

    public Provides[] getProvides() {
        return provides;
    }
}
