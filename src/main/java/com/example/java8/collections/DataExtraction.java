package com.example.java8.collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

/**
 * The Class DataExtraction.
 */
public class DataExtraction {
	
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
		
		// Find first match after filter
		people.stream().filter(p -> p.getName().startsWith("A")).findFirst().ifPresent(System.out::println);

		// True if any match
		System.out.println(people.stream().anyMatch(p -> p.getName().startsWith("A")));
		
		// True if at least one element meet condition
		System.out.println(people.stream().allMatch(p -> p.getName().startsWith("A")));
		
		// True if all elements meet condition (force with filter)
		System.out.println(people.stream().filter(p -> p.getName().startsWith("A")).allMatch(p -> p.getName().startsWith("A")));
		
		// Print all distinct cars that people have
		System.out.println(people.stream()
			.filter(p -> p.getName().startsWith("A"))
			.map(Person::getCars).flatMap(List::stream)
			.distinct()
			.onClose(() -> System.out.println("Finish list cars"))
			.collect(Collectors.joining(",")));
		
	}

}
