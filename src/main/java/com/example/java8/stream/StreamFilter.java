package com.example.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

public class StreamFilter {
	/** The Constant FILE. */
	private static final String FILE = "src/main/resources/data.csv";

	public static void main(String[] args) throws IOException {
		
		List<Person> people = Files.lines(Paths.get(FILE)).map(line -> { 
			String[] splitedLine = line.split(";");
			return new Person(splitedLine[0], splitedLine[1], splitedLine[2], splitedLine[3]);
		}).collect(Collectors.toList());

		System.out.println("Name Start with A");
		people.stream().filter(p -> p.getName().startsWith("A")).forEach(System.out::println);

		
		System.out.println("\\n\\nBirthday year is 2000");
		people.stream()
			.filter(p -> p.getBirthday().getYear()==2000)
			.forEach(System.out::println);

		System.out.println("\\n\\nBirthday is 4 july");
		people.stream()
			.filter(p -> p.getBirthday().getMonth().compareTo(Month.JULY)==0)
			.filter(p -> p.getBirthday().getDayOfMonth()==4)
			.forEach(System.out::println);

		//Add emails to array
		List<String> emails = new ArrayList();
		people.stream()
			.filter(p -> p.getBirthday().getMonth().compareTo(Month.JULY)==0)
			.filter(p -> p.getBirthday().getDayOfMonth()==4)
			.forEach( c -> {
				emails.add(c.getMail());
			}
		);
		
		System.out.println("\\n\\nOnly one cars");
		people.stream()
			.filter(p -> p.getCars().size() == 1)
			.forEach(System.out::println);
		
		System.out.println("\\n\\nMore than three cars and one is Jaguar");
		people.stream()
			.filter(p -> p.getCars().size() > 3)
			.filter(p -> p.getCars().contains("Jaguar"))
			.forEach(System.out::println);

		System.out.println("\n\nThe old");
		Comparator<Person> comp = (p1, p2) -> p1.getBirthday().compareTo(p2.getBirthday());
    	people.stream().min(comp).ifPresent(p -> System.out.println(p.getName() + " " + p.getBirthday()));
	}
	
}
