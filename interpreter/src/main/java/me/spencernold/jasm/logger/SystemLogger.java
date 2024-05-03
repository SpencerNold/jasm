package me.spencernold.jasm.logger;

import java.io.OutputStream;

public class SystemLogger extends Logger {

    private static final SystemLogger INSTANCE = new SystemLogger();

    private SystemLogger() {}

    @Override
    public OutputStream getOutputStream() {
        return System.out;
    }

    @Override
    public OutputStream getErrorStream() {
        return System.err;
    }

    public static SystemLogger getInstance() {
        return INSTANCE;
    }
}
