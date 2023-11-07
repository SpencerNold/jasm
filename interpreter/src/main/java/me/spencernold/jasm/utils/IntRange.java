package me.spencernold.jasm.utils;

public class IntRange {

	private int min, max;
	
	public IntRange(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Checks if {@code n} is in the range of [min, max)
	 * 
	 * @param n number to check
	 * @return true of in range, false if out of range
	 */
	public boolean isInRangeLowerBoundInclusive(int n) {
		return n >= min && n < max;
	}
	
	/**
	 * Checks if {@code n} is in the range of (min, max]
	 * 
	 * @param n number to check
	 * @return true of in range, false if out of range
	 */
	public boolean isInRangeUpperBoundInclusive(int n) {
		return n > min && n <= max;
	}
	
	/**
	 * Checks if {@code n} is in the range of (min, max)
	 * 
	 * @param n number to check
	 * @return true of in range, false if out of range
	 */
	public boolean isInRangeExclusive(int n) {
		return n > min && n < max;
	}
	
	/**
	 * Checks if {@code n} is in the range of [min, max]
	 * 
	 * @param n number to check
	 * @return true of in range, false if out of range
	 */
	public boolean isInRange(int n) {
		return n >= min && n <= max;
	}
	
	public int getMinimum() {
		return min;
	}
	
	public int getMaximum() {
		return max;
	}
}
