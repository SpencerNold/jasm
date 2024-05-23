package me.spencernold.llvm.utils;

import me.spencernold.llvm.binding.NumericType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptorTool {

    public static ArrayList<Type> getDescriptorTypeList(String descriptor) {
        ArrayList<Type> list = new ArrayList<>();
        Iterator<Type> iterator = getDescriptorTypeIterator(descriptor);
        while (iterator.hasNext())
            list.add(iterator.next());
        return list;
    }

    public static Iterator<Type> getDescriptorTypeIterator(String descriptor) {
        Pattern pattern = Pattern.compile("(V|Z|B|C|S|I|J|F|D|(L.+;))");
        Matcher matcher = pattern.matcher(descriptor);
        return new Iterator<Type>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public Type next() {
                String match = matcher.group(1);
                if (match.startsWith("L") && match.endsWith(";"))
                    return Type.POINTER;
                else if (match.charAt(0) == 'Z')
                    return Type.BOOLEAN;
                else if (match.charAt(0) == 'B')
                    return Type.BYTE;
                else if (match.charAt(0) == 'C')
                    return Type.CHAR;
                else if (match.charAt(0) == 'S')
                    return Type.SHORT;
                else if (match.charAt(0) == 'I')
                    return Type.INT;
                else if (match.charAt(0) == 'J')
                    return Type.LONG;
                else if (match.charAt(0) == 'F')
                    return Type.FLOAT;
                else if (match.charAt(0) == 'D')
                    return Type.DOUBLE;
                else if (match.charAt(0) == 'V')
                    return Type.VOID;
                throw new IllegalArgumentException(descriptor + " is not a valid descriptor");
            }
        };
    }

    public enum Type {
        POINTER, BOOLEAN, BYTE, CHAR, SHORT, INT, LONG, FLOAT, DOUBLE, VOID;
    }
}
