package me.spencernold.llvm.exceptions;

public class PreprocessException extends RuntimeException {

    public PreprocessException(String format, Object... args) {
        this(String.format(format, args));
    }

    public PreprocessException(String message) {
        super(message);
    }

    public PreprocessException(Throwable e) {
        super(e);
    }
}
