package me.spencernold.assembler.options;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OptionParser {

    private final HashMap<String, Class<? extends Option<?>>> options = new HashMap<>();

    private final String[] arguments;
    private final OutputStream outputStream, errorStream;

    public OptionParser(String[] args) {
        this(args, System.out, System.err);
    }

    public OptionParser(String[] args, OutputStream outputStream, OutputStream errorStream) {
        this.arguments = args;
        this.outputStream = outputStream;
        this.errorStream = errorStream;
    }

    public void register(String name, Class<? extends Option<?>> type) {
        options.put(name, type);
    }

    public OptionContext parse() {
        HashMap<String, Object> body = new HashMap<>();
        ArrayList<String> flags = new ArrayList<>();
        Pattern pattern = Pattern.compile("--(.+)=(.+)$");
        int i;
        for (i = 0; i < arguments.length; i++) {
            String arg = arguments[i];
            Matcher matcher = pattern.matcher(arg);
            if (matcher.matches()) {
                String key = matcher.group(1);
                if (!options.containsKey(key)) {
                    printOutFormat(errorStream, "unknown argument: \"%S\"", key);
                    return OptionContext.empty();
                }
                String value = matcher.group(2);
                Option<?> option = construct(options.get(key), value);
                if (option == null) {
                    printOutFormat(errorStream, "argument \"%s\" must be of the correct type");
                    return OptionContext.empty();
                }
                Object object = option.read();
                if (object == null) {
                    printOutFormat(errorStream, "failed to read argument: \"%s\"", key);
                    return OptionContext.empty();
                }
                body.put(key, object);
            } else if (arg.startsWith("-") && arg.length() == 2)
                flags.add(arg);
        }
        String[] args = Arrays.copyOfRange(arguments, i, arguments.length);
        return new OptionContext(body, flags, args);
    }

    private <T> T construct(Class<T> clazz, String arg) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(String.class);
            return constructor.newInstance(arg);
        } catch (Exception e) {
            return null;
        }
    }

    private void printOutFormat(OutputStream stream, String format, Object... arguments) {
        try {
            stream.write(String.format(format + "\n", arguments).getBytes(StandardCharsets.UTF_8));
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
