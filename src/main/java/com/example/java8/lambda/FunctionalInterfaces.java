package com.example.java8.lambda;

import java.util.Arrays;

/**
 * The Class FunctionalInterfaces.
 */
public class FunctionalInterfaces {
	
	/**
	 * The Interface BinaryMathOperation.
	 */
	@FunctionalInterface
	interface BinaryMathOperation {
		
		/**
		 * Operation.
		 *
		 * @param a the a
		 * @param b the b
		 * @return the double
		 */
		public double operation(double a, double b);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		
		BinaryMathOperation sum = (double a, double b) -> a + b;
		BinaryMathOperation sub = (a, b) -> a - b;		
		BinaryMathOperation div = (a, b) -> { return a / b; };		
		BinaryMathOperation mul = (double a, double b) -> { return a * b; };
		
		double a = 10;
		double b = 5;
		
		Arrays.asList(sum.operation(a, b), sub.operation(a, b), div.operation(a, b), mul.operation(a, b)).forEach(System.out::println);
		
	}

}
