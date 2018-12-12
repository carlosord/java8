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
		
		System.out.println("Concat emails");
		System.out.println(people.stream().map(p -> p.getMail()).reduce("", (a,b) -> a + ", " + b));
		
		System.out.println("Upper names");
		people.stream().filter(p -> p.getName().startsWith("J")).map(p -> p.getName().toUpperCase()).forEach(System.out::println);
		
	}
}
