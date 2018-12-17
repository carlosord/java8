package com.example.java8.collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Class CollectionOperations.
 */
public class CollectionOperations {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
				
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		
		// Put only if b not exist
		map.putIfAbsent("b", 4);
		System.out.println(map.get("b"));
		
		// Get a default value
		System.out.println(map.getOrDefault("d", 5));
		
		map.putIfAbsent("d", 4);
		System.out.println(map.get("d"));
		
		// Replace par values for 7 and other for 3
		map.replaceAll((k, v) -> v % 2 == 0 ? 7 : 3);
		System.out.println(map);
		
		map.compute("d", (k, v) -> v + 5);
		System.out.println(map.get("d"));
		
		map.merge("d", 2, (x, y) -> x * y);
		System.out.println(map.get("d"));
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		List<Integer> list2 = Arrays.asList(1, 2, 3);
		
		list.removeIf(x -> x % 2 == 0);
		System.out.println(list);
		
		try {
			list2.removeIf(x -> x % 2 == 0);
			System.out.println(list2);
		} catch (UnsupportedOperationException e) {
			System.out.println("With Arrays.asList... removeIf doesn't run");
		}
		
		// Fix to Arrays.asList
		List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3));
		
		list3.removeIf(x -> x % 2 == 0);
		System.out.println(list3);
		
		Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
		
		set.removeIf(x -> x + 5 == 6);
		System.out.println(set);
		
		// Foreach with consumer
		list.forEach(System.out::println);
		
		// Foreach with more than one operation
		Float constant = new Float(2);
		AtomicInteger ai = new AtomicInteger(2);
		list.forEach(x -> { 
			Float mul = x * constant;
			Float div = x / constant;
			// Error because constant is not in the same context to modify... only to get value
			//constant += 4;
			// to fix, better use an atomic number
			Float newConstant = new Float(ai.incrementAndGet());
			System.out.println(String.format("Modifiy value of external variable to: %.0f", newConstant));
			System.out.println(String.format("Mul of %d by %.0f is %.1f. Div of %d by %.0f is %.1f", x, constant, mul, x, constant, div));
		});
		
		
		
	}

}
