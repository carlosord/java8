package com.example.java8.lambda;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The Class PassingCodeToMethodsWithParams.
 */
public class PassingCodeToMethodsWithParams {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		System.out.println("Sum 10 + 5. Result: " + doSomething(10, 5, (x, y) -> (int) x + (int) y));
		System.out.println("Mul 10 + 5. Result: " + doSomething(10, 5, (x, y) -> (int) x * (int) y));
		
		System.out.println("Sum 10 + 5. Result: " + doSomething(() -> 10 + 5));
		System.out.println("Mul 10 + 5. Result: " + doSomething(() -> 10 * 5));
				
		System.out.println("Say to 'carlosord'. Result: " + doSomething("carlosord", x -> String.format("Hello %s!", x)));
		System.out.println("Sum array {1, 2, 3}. Result: " + doSomething(new int[]{1,2,3}, x -> Arrays.stream((int[])x).sum()));
		
	}
	
	/**
	 * Do something (without params).
	 * 
	 * @param supplier the supplier
	 * @return the object
	 */
	private static Object doSomething(Supplier<Object> supplier) {
		System.out.println("Simulates operation 1 (after)");
		System.out.println("Simulates operation 2 (after)");
		
		Object ret = supplier.get();
		
		System.out.println("Simulates operation 3 (before)");
		System.out.println("Simulates operation 4 (before)");
		
		return ret;
	}
	
	/**
	 * Do something (with one param).
	 *
	 * @param param the param
	 * @param fn the fn
	 * @return the object
	 */
	public static Object doSomething(Object param, Function<Object, Object> fn) {
		System.out.println("Simulates operation 1 (after)");
		System.out.println("Simulates operation 2 (after)");
		
		Object ret = fn.apply(param);
		
		System.out.println("Simulates operation 3 (before)");
		System.out.println("Simulates operation 4 (before)");
		
		return ret;
	}
	
	/**
	 * Do something (with two params).
	 *
	 * @param param1 the param 1
	 * @param param2 the param 2
	 * @param fn the fn
	 * @return the object
	 */
	public static Object doSomething(Object param1, Object param2, BiFunction<Object, Object, Object> fn) {
		System.out.println("Simulates operation 1 (after)");
		System.out.println("Simulates operation 2 (after)");
		
		Object ret = fn.apply(param1, param2);
		
		System.out.println("Simulates operation 3 (before)");
		System.out.println("Simulates operation 4 (before)");
		
		return ret;
	}
	
}
