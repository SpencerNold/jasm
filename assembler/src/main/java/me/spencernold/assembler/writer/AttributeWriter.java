package me.spencernold.assembler.writer;

import me.spencernold.assembler.writer.attributes.CodeAttrWriter;
import me.spencernold.assembler.writer.attributes.ConstValueAttrWriter;
import me.spencernold.assembler.writer.attributes.GenericAttrWriter;
import me.spencernold.jasm.intermediary.JWritable;
import me.spencernold.jasm.intermediary.attributes.Attribute;
import me.spencernold.jasm.intermediary.attributes.Body;
import me.spencernold.jasm.intermediary.attributes.impl.CodeAttribute;
import me.spencernold.jasm.intermediary.attributes.impl.ConstValueAttribute;
import me.spencernold.jasm.intermediary.pools.AttributePool;
import me.spencernold.jasm.intermediary.pools.ConstPool;

import java.util.HashMap;

public class AttributeWriter implements JWritable<StringWriter> {

    private final HashMap<Class<? extends Body>, InputJWritable<StringWriter, Body>> writers = new HashMap<>();
    private final GenericAttrWriter genericAttrWriter = new GenericAttrWriter();

    private final ConstPool constPool;
    private final AttributePool attributePool;

    public AttributeWriter(ConstPool constPool, AttributePool attributePool) {
        this.constPool = constPool;
        this.attributePool = attributePool;
        writers.put(ConstValueAttribute.class, new ConstValueAttrWriter(constPool));
        writers.put(CodeAttribute.class, new CodeAttrWriter(constPool));
    }

    @Override
    public void write(StringWriter writer) {
        if (attributePool.getAttributeCount() == 0)
            return;
        boolean verbose = writer.hasVerboseFlag();
        writer.println("attributes {");
        writer.incrementTabCount();
        boolean isFirstIndex = true;
        for (Attribute attribute : attributePool) {
            if (!isFirstIndex)
                writer.println();
            if (verbose)
                writer.println("// " + attribute.getName());
            writer.printf("#%d {\n", attribute.getNameIndex());
            writers.getOrDefault(attribute.getBody().getClass(), genericAttrWriter).write(writer, attribute.getBody());
            writer.printf("}\n");
            isFirstIndex = false;
        }
        writer.decrementTabCount();
        writer.println("}");
    }
}
