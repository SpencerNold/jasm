package me.spencernold.llvm.binding;

import me.spencernold.llvm.utils.DescriptorTool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Type {

    private static final Type VOID = new Type(0);

    final long handle;

    public Type(long handle) {
        this.handle = handle;
    }

    public static Type[] getFromDescriptor(Module module, String desc) {
        Iterator<DescriptorTool.Type> iterator = DescriptorTool.getDescriptorTypeIterator(desc);
        List<Type> types = new ArrayList<>();
        while (iterator.hasNext()) {
            DescriptorTool.Type type = iterator.next();
            if (type == DescriptorTool.Type.POINTER)
                types.add(module.getNumericType(NumericType.POINTER));
            else if (type == DescriptorTool.Type.BOOLEAN)
                types.add(module.getNumericType(NumericType.BOOLEAN));
            else if (type == DescriptorTool.Type.BYTE)
                types.add(module.getNumericType(NumericType.BYTE));
            else if (type == DescriptorTool.Type.CHAR)
                types.add(module.getNumericType(NumericType.CHAR));
            else if (type == DescriptorTool.Type.SHORT)
                types.add(module.getNumericType(NumericType.SHORT));
            else if (type == DescriptorTool.Type.INT)
                types.add(module.getNumericType(NumericType.INT));
            else if (type == DescriptorTool.Type.LONG)
                types.add(module.getNumericType(NumericType.LONG));
            else if (type == DescriptorTool.Type.FLOAT)
                types.add(module.getNumericType(NumericType.FLOAT));
            else if (type == DescriptorTool.Type.DOUBLE)
                types.add(module.getNumericType(NumericType.DOUBLE));
            else if (type == DescriptorTool.Type.VOID)
                types.add(VOID);
        }
        return types.toArray(new Type[0]);
    }
}
