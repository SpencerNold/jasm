package me.spencernold.jasm.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class Logger {

    public void log(Level level, String format, Object... args) {
        switch (level) {
            case INFO:
                info(format, args);
                break;
            case WARNING:
                warn(format, args);
                break;
            case ERROR:
                error(format, args);
                break;
        }
    }

    public void info(String format, Object... args) {
        print(getOutputStream(), "[INFO] " + String.format(format, args) + "\n");
    }

    public void warn(String format, Object... args) {
        print(getErrorStream(), "[WARN] " + String.format(format, args) + "\n");
    }

    public void error(String format, Object... args) {
        print(getErrorStream(), "[ERROR] " + String.format(format, args) + "\n");
    }

    private void print(OutputStream output, String message) {
        try {
            output.write(message.getBytes(StandardCharsets.UTF_8));
            output.flush();
        } catch (IOException ignored) {}
    }

    public abstract OutputStream getErrorStream();
    public abstract OutputStream getOutputStream();

    public enum Level {
        INFO, WARNING, ERROR;
    }
}
