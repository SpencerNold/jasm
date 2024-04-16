package me.spencernold.assembler.writer;

import me.spencernold.assembler.writer.access.AccessTranslator;
import me.spencernold.jasm.intermediary.JClass;
import me.spencernold.jasm.intermediary.JWritable;
import me.spencernold.jasm.intermediary.constants.Constant;
import me.spencernold.jasm.intermediary.constants.ReferenceConstant;

import java.util.Arrays;

public class AssemblyWriter implements JWritable<StringWriter> {

    private final AccessTranslator accessTranslator = new AccessTranslator.Factory(AccessTranslator.Type.CLASS).create();
    private final JClass jclass;

    private final ConstPoolWriter constPoolWriter;
    private final FieldWriter fieldWriter;
    private final MethodWriter methodWriter;
    private final AttributeWriter attributeWriter;

    public AssemblyWriter(JClass jclass) {
        this.jclass = jclass;
        this.constPoolWriter = new ConstPoolWriter(jclass.getConstPool());
        this.fieldWriter = new FieldWriter(jclass.getConstPool(), jclass.getFieldPool());
        this.methodWriter = new MethodWriter(jclass.getConstPool(), jclass.getMethodPool());
        this.attributeWriter = new AttributeWriter(jclass.getConstPool(), jclass.getAttributePool());
    }

    @Override
    public void write(StringWriter writer) {
        boolean verbose = writer.hasVerboseFlag();
        constPoolWriter.write(writer);
        writer.println("class {");
        writer.incrementTabCount();
        if (verbose)
            writer.printf("// %s %s extends %s\n", accessTranslator.translate(jclass.getAccess()), jclass.getClassName(), jclass.getSuperName());
        writer.printf("minor: 0x%02x\n", jclass.getMinorVersion());
        writer.printf("major: 0x%02x\n", jclass.getMajorVersion());
        writer.printf("access: 0x%04x\n", jclass.getAccess());
        writer.println("className: #" + jclass.getClassNameIndex());
        writer.println("superName: #" + jclass.getSuperNameIndex());
        Short[] interfaces = jclass.getInterfacePool().getInterfaces();
        String[] istrs = new String[interfaces.length];
        if (verbose && interfaces.length != 0) {
            for (int i = 0; i < interfaces.length; i++)
                istrs[i] = jclass.getConstPool().getStringFromReferenceConstant(interfaces[i]);
            writer.printf("// [%s]\n", String.join(", ", istrs));
        }
        for (int i = 0; i < interfaces.length; i++)
            istrs[i] = "#" + interfaces[i];
        writer.printf("interfaces: [%s]\n", String.join(", ", istrs));
        fieldWriter.write(writer);
        methodWriter.write(writer);
        attributeWriter.write(writer);
        writer.decrementTabCount();
        writer.println("}\n");
    }
}
