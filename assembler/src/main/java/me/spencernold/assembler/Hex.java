package me.spencernold.assembler;

public class Hex {

	public static String hexify(int i) {
		return Integer.toUnsignedString(i, 16).toUpperCase();
	}
}
