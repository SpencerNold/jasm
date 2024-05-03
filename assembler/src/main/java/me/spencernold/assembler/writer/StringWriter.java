package me.spencernold.assembler.writer;

import me.spencernold.jasm.options.OptionContext;

public class StringWriter {

    private final StringBuilder builder;
    private final OptionContext context;
    private String tabCountString = "";
    private String tabString;

    public StringWriter(OptionContext context) {
        this.builder = new StringBuilder();
        this.context = context;
        this.tabString = context.hasOption("tab") ? new String(new byte[context.getAsInteger("tab")]).replace("\0", " ") : "\t";
    }

    public void println(Object line) {
        builder.append(tabCountString).append(line).append("\n");
    }

    public void println() {
        builder.append("\n");
    }

    public void printf(String format, Object... arguments) {
        builder.append(tabCountString).append(String.format(format, arguments));
    }

    public void incrementTabCount() {
        tabCountString += tabString;
    }

    public void decrementTabCount() {
        tabCountString = tabCountString.substring(tabString.length());
    }

    public OptionContext getOptionContext() {
        return context;
    }

    public boolean hasVerboseFlag() {
        return context.hasFlag("-v");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
