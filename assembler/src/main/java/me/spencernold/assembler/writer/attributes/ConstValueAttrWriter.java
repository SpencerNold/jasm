package me.spencernold.assembler.writer.attributes;

import me.spencernold.assembler.writer.InputJWritable;
import me.spencernold.assembler.writer.StringWriter;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.ConstValueAttribute;
import me.spencernold.jasm.intermediary.pools.ConstPool;

public class ConstValueAttrWriter implements InputJWritable<StringWriter, Body> {

    private final ConstPool constPool;

    public ConstValueAttrWriter(ConstPool constPool) {
        this.constPool = constPool;
    }

    @Override
    public void write(StringWriter writer, Body body) {
        writer.incrementTabCount();
        boolean verbose = writer.hasVerboseFlag();
        int index = ((ConstValueAttribute) body).getConstValueIndex();
        if (verbose) {
            writer.println("//");
        }
        writer.println("#" + index);
        writer.decrementTabCount();
    }
}
