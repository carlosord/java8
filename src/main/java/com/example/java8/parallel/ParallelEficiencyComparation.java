package com.example.java8.parallel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

/**
 * The Class ParallelEficiencyComparation.
 */
public class ParallelEficiencyComparation {
	
	/** The Constant FILE. */
	private static final String FILE = "src/main/resources/data.csv";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		
		List<Person> people = Files.lines(Paths.get(FILE)).map(line -> { 
			String[] splitedLine = line.split(";");
			return new Person(splitedLine[0], splitedLine[1], splitedLine[2], splitedLine[3]);
		}).collect(Collectors.toList());
		
		Long t1, t2;
		
		List<Person> result = new ArrayList<>();
		// Data dates are between 1970 & 2000
		LocalDate max_date = LocalDate.of(1985, 1, 1);
		
		final Function<Person, Boolean> condition = p -> p.getBirthday().isBefore(max_date);
		
		/**
		 * 
		 * 
		 * FOR
		 * 
		 * 
		 */		
		System.out.println("Inicio primer metodo");
		t1 = System.currentTimeMillis();
		for (int i = 0; i < people.size(); i++) {
			Person p = people.get(i);
			sleep();
			if (condition.apply(p)) {
				result.add(p);
			}
		}
		t2 = System.currentTimeMillis();
		
		System.out.println("Result: " + result.size() + ", Time (for): " + (t2 - t1) + "ms");
		result.clear();
		
		/**
		 * 
		 * 
		 * FOREACH
		 * 
		 * 
		 */
		System.out.println("Inicio segundo metodo");
		t1 = System.currentTimeMillis();
		for (Person p: people) {
			sleep();
			if (condition.apply(p)) {
				result.add(p);
			}
		}
		t2 = System.currentTimeMillis();
		
		System.out.println("Result: " + result.size() + ", Time (foreach): " + (t2 - t1) + "ms");
		result.clear();
		
		/**
		 * 
		 * 
		 * STREAM
		 * 
		 * 
		 */		
		System.out.println("Inicio tercer metodo");
		t1 = System.currentTimeMillis();
		result = people.stream().filter(p -> {
			sleep();
			return condition.apply(p);
		}).collect(Collectors.toList());
		t2 = System.currentTimeMillis();
		
		System.out.println("Result: " + result.size() + ", Time (stream): " + (t2 - t1) + "ms");
		result.clear();
		
		/**
		 * 
		 * 
		 * STREAM - PARALLEL
		 * 
		 * 
		 */		
		System.out.println("Inicio cuarto metodo");
		t1 = System.currentTimeMillis();
		result = people.stream().parallel().filter(p -> {
			sleep();
			return condition.apply(p);
		}).collect(Collectors.toList());
		t2 = System.currentTimeMillis();
		
		System.out.println("Result: " + result.size() + ", Time (stream - parallel): " + (t2 - t1) + "ms");
		result.clear();
		
		/**
		 * 
		 * 
		 * PARALLELSTREAM
		 * 
		 * 
		 */		
		System.out.println("Inicio quinto metodo");
		t1 = System.currentTimeMillis();
		result = people.parallelStream().filter(p -> {
			sleep();
			return condition.apply(p);
		}).collect(Collectors.toList());
		t2 = System.currentTimeMillis();
		
		System.out.println("Result: " + result.size() + ", Time (parallelStream): " + (t2 - t1) + "ms");
		result.clear();
				
	}
	
	/**
	 * Sleep. Simulates an operation that takes 1ns
	 */
	private static void sleep() {
		try {
			Thread.sleep(0, 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
