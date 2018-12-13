package com.example.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

public class StreamMap {
	/** The Constant FILE. */
	private static final String FILE = "src/main/resources/data.csv";

	public static void main(String[] args) throws IOException {
		
		List<Person> people = Files.lines(Paths.get(FILE)).map(line -> { 
			String[] splitedLine = line.split(";");
			return new Person(splitedLine[0], splitedLine[1], splitedLine[2], splitedLine[3]);
		}).collect(Collectors.toList());
		
		System.out.println("All names 1");
		people.stream()
			.map(p -> p.getName()).forEach(System.out::println);

		System.out.println("All names 2");
		people.stream()
			.map(Person::getName).forEach(System.out::println);

		System.out.println("All days of the birthday");
		people.stream()
			.map(p -> p.getBirthday().getDayOfMonth()).forEach(System.out::println);

		System.out.println("Sum od the days of the birdthdays");
		System.out.println(
			people.stream()
				.mapToInt(p -> p.getBirthday().getDayOfMonth()).sum()
		);

		System.out.println("Upper names which start with J");
		people.stream()
			.filter(p -> p.getName().startsWith("J"))
			.map(p -> p.getName().toUpperCase())
			.forEach(System.out::println);

		System.out.println("Concat emails");
		System.out.println(
			people.stream()
				.map(p -> p.getMail())
				.reduce("", (a,b) -> a + ", " + b)
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
			.map(c -> c.toString())
			.distinct()
			.sorted()
			.forEach(System.out::println);

		System.out.println("Car brands 2");
		people.stream()
			.map(p -> p.getCars())
			.flatMap(c -> c.stream())
			.map(c -> c.toString())
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
            .map( e -> e.getKey())
			.forEach(System.out::println);

		System.out.println("Two filters: emails with lees than 20 characters of people which name starts with A");
		people.stream()
			.filter(p -> p.getName().startsWith("A"))
			.map(p -> p.getMail())
			.distinct()
			.sorted()
			.filter(m -> m.length() < 20)
			.forEach(System.out::println);

	}
}
