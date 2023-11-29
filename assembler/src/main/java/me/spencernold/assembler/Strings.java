package me.spencernold.assembler;

import java.util.Arrays;

public class Strings {

	public static String generateStringWithNCharacters(int n, char c) {
		char[] chars = new char[n];
		Arrays.fill(chars, c);
		return new String(chars);
	}
}
