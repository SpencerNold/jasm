package me.spencernold.jasm.exceptions;

public class ClassFormatException extends RuntimeException {

	private static final long serialVersionUID = -2256809117347200346L;
	
	public ClassFormatException(Type type, String message, Object... arguments) {
		super(type.message + ": " + String.format(message, arguments));
	}
	
	public ClassFormatException(Type type) {
		super(type.message);
	}
	
	public enum Type {
		
		NOT_CLASS("target is not a valid .class file type"),
		MALFORMED("target class is malformed");
		
		private String message;
		
		private Type(String message) {
			this.message = message;
		}
	}
}
