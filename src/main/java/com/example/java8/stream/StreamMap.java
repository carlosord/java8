package com.example.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.java8.model.Person;

public class StreamMap {
	
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
		
		System.out.println("All names 1 (lambda)");
		people.stream()
			.map(p -> p.getName()).forEach(System.out::println);

		System.out.println("All names 2 (Method reference)");
		people.stream()
			.map(Person::getName).forEach(System.out::println);

		System.out.println("All days of the birthday");
		people.stream()
			.map(p -> p.getBirthday().getDayOfMonth()).forEach(System.out::println);

		System.out.println("Sum the days of the birthdays");
		System.out.println(
			people.stream()
				.mapToInt(p -> p.getBirthday().getDayOfMonth()).sum()
		);

		System.out.println("Upper names which start with J");
		people.stream()
			.filter(p -> p.getName().startsWith("J"))
			.map(p -> p.getName().toUpperCase())
			.forEach(System.out::println);

		System.out.println("Concat emails (reduce)");
		System.out.println(
			people.stream()
				.map(Person::getMail)
				.reduce("", (a,b) -> a + ", " + b)
		);
		
		System.out.println("Concat emails (collect)");
		System.out.println(
			people.stream()
				.map(Person::getMail)
				.collect(Collectors.joining(","))
		);
		
		System.out.println("Years of birthdays");
		people.stream()
			.map(p -> p.getBirthday().getYear())
			.distinct()
			.sorted()
			.forEach(System.out::println);

		System.out.println("Car brands 1");
		people.stream()
			.flatMap(p -> p.getCars().stream())
			.distinct()
			.sorted()
			.forEach(System.out::println);

		System.out.println("Car brands 2");
		people.stream()
			.map(Person::getCars)
			.flatMap(List::stream)
			.distinct()
			.sorted()
			.forEach(System.out::println);

		System.out.println("Count brands duplicates");
		System.out.println(
			people.stream()
				.flatMap(p -> p.getCars().stream())
				.collect( Collectors.groupingBy( c -> c, Collectors.counting()))
		);

		System.out.println("First names which apear more than 300 times");
		people.stream()
			.map(p -> p.getName().split(" ")[0])
			.collect( Collectors.groupingBy( n -> n, Collectors.counting()))
			.entrySet()
            .stream()
            .filter( p -> p.getValue() > 300 )
            .map(Entry::getKey)
			.forEach(System.out::println);

		System.out.println("Two filters: emails with lees than 20 characters of people which name starts with A and sorted");
		people.stream()
			.filter(p -> p.getName().startsWith("A"))
			.map(Person::getMail)
			.distinct()
			.filter(m -> m.length() < 20)
			.sorted()
			.forEach(System.out::println);

		System.out.println("Update data: update email people with name Jose");
		Stream<Person> s = people.stream()
			.filter(p -> p.getName().startsWith("J"))
			.filter(p -> p.getName().contains("Cuevas"));
		
		s.forEach(p -> {
			System.out.println(p);
			p.setMail(p.getMail().replace("nomail", "newnomail"));
			System.out.println(p+"\n");
		});
	}
}
