package me.spencernold.llvm.jar;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManifestElement extends ResourceElement {

    private static final Pattern PATTERN = Pattern.compile("(.+): (.+)");

    private final HashMap<String, String> keyValuePairs = new HashMap<>();

    public ManifestElement(String name, byte[] body) {
        super(name, body);
        Matcher matcher = PATTERN.matcher(new String(body, StandardCharsets.UTF_8));
        while (matcher.find())
            keyValuePairs.put(matcher.group(1), matcher.group(2));
    }

    public String get(String key) {
        return keyValuePairs.get(key);
    }
}
