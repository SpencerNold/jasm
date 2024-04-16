package me.spencernold.assembler.options;

import me.spencernold.assembler.options.impl.FileOptionType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionContext {

    private final Map<String, Object> options;
    private final List<String> flags;
    private final String[] arguments;

    public OptionContext(Map<String, Object> options, List<String> flags, String[] arguments) {
        this.options = options;
        this.flags = flags;
        this.arguments = arguments;
    }

    public boolean hasOption(String name) {
        return options.containsKey(name);
    }

    public boolean hasFlag(String name) {
        return flags.contains(name);
    }

    public String getAsString(String name) {
        return (String) options.get(name);
    }

    public File getAsFile(String name) {
        return (File) options.get(name);
    }

    public int getAsInteger(String name) {
        return (Integer) options.get(name);
    }

    public float getAsDecimal(String name) {
        return (Float) options.get(name);
    }

    public String[] getArgumentsAsStrings() {
        return arguments;
    }

    public File[] getArgumentsAsFiles() {
        File[] files = new File[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            FileOptionType option = new FileOptionType(arguments[i]);
            files[i] = option.read();
        }
        return files;
    }

    public static OptionContext empty() {
        return new OptionContext(new HashMap<>(), new ArrayList<>(), new String[0]);
    }
}
