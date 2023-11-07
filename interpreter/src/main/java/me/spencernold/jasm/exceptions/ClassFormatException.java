package me.spencernold.jasm.exceptions;

/**
 * Generic exception for the instances where the ClassReader fails to read a
 * Java class.
 * 
 * @author Spencer Nold
 * @since 1.0.0
 * @see me.spencernold.jasm.ClassReader
 */
public class ClassFormatException extends RuntimeException {

	private static final long serialVersionUID = -2256809117347200346L;

	/**
	 * Thrown when the {@code ClassReader} fails to read an input class.
	 * 
	 * @param type type of format exception
	 * @param message format string for the message of the exception
	 * @param arguments arguments for the format of the message
	 */
	public ClassFormatException(Type type, String message, Object... arguments) {
		super(type.message + ": " + String.format(message, arguments));
	}

	/**
	 * Thrown when the {@code ClassReader} fails to read an input class.
	 * 
	 * @param type type of format exception
	 */
	public ClassFormatException(Type type) {
		super(type.message);
	}

	public enum Type {

		NOT_CLASS("target is not a valid .class file type"), MALFORMED("target class is malformed");

		private String message;

		private Type(String message) {
			this.message = message;
		}
	}
}
