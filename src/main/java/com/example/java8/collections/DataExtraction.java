package com.example.java8.collections;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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
		
		// Create a list with all names witch starts with "Mario C" 1
		List l1 = people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.map(Person::getName)
			.sorted()
			.collect(Collectors.toList());
		System.out.println("List size: " + l1.size());
		l1.forEach(System.out::println);

		// Create a list with all names witch starts with "Mario C" 2
		people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.collect(Collectors.mapping(Person::getName, Collectors.toList()))
			.forEach(System.out::println);	
			
		// Create a set with all names witch starts with "Mario C"
		Set s1 = people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.map(Person::getName)
			.sorted()
			.collect(Collectors.toSet());
		System.out.println("Set size: " + s1.size());
		s1.forEach(System.out::println);
		
		
		// Create a map with all names witch starts with "Mario C". The key will be the email, and the value the Cars 1
		people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.collect(Collectors.toMap(Person::getMail, Person::getCars))
			.forEach((k,v) -> System.out.println(k + " => " + v));

		// Create a map with all names witch starts with "Mario C". The key will be the email, and the value the Cars 2
		people.stream()
		.filter(p -> p.getName().startsWith("Mario C"))
        .collect(HashMap::new, (k,v)->k.put(v.getMail(), v.getCars()), HashMap::putAll)
        .forEach((k,v) -> System.out.println(k + " => " + v));

		// Create a map with all names witch starts with "Mario C". The key will be the name, and the value the Cars. 
		//We only keep the car list of the first name
		people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.collect(Collectors.toMap(Person::getName, Person::getCars, (x,y) -> {return x;}))
			.forEach((k,v) -> System.out.println(k + " => " + v));

		//Create a map, grouping by name. The key is the name, the value will be the List of persons
		people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.collect(Collectors.groupingBy(Person::getName))
			.forEach((k,v) -> System.out.println(k + " => " + v));
		
		//Create a map, grouping by name. The key is the name, the value will be the List of emails
		people.stream()
			.filter(p -> p.getName().startsWith("Mario C"))
			.collect(Collectors.groupingBy(
					Person::getName, 
					Collectors.mapping(Person::getMail, Collectors.toList())))
			.forEach((k,v) -> System.out.println(k + " => " + v));
		
		//Create a map, grouping by birthday (on february of 1987). The value will be the number of persons which birthday is that day
		people.stream()
		.filter(p -> p.getBirthday().getYear()==1987)
		.filter(p -> p.getBirthday().getMonth()==Month.FEBRUARY)
	    .collect(Collectors.groupingBy(
	            Person::getBirthday,
	            Collectors.counting())).forEach((k,v) -> System.out.println(k + " => " + v));
	
		//Create a map, grouping by first chart of name. The values will be a list of emails
		people.stream()
			.collect(Collectors.groupingBy(p -> p.getName().charAt(0), Collectors.mapping(Person::getMail, Collectors.joining(", "))))
			.forEach((k,v) -> System.out.println(k + " => " + v));
		
		//Create a map, grouping by first chart of name. The values will be the person which Birthday is later
		people.stream()
		.collect(Collectors.groupingBy(p -> p.getName().charAt(0), Collectors.maxBy(Comparator.comparing(Person::getBirthday))))
		.forEach((k,v) -> System.out.println(k + " => " + v));

		//Create a map, grouping by first chart of name. The values will be the sum of the number of cars
		people.stream()
		.collect(Collectors.groupingBy(p -> p.getName().charAt(0), Collectors.reducing(0, p -> p.getCars().size(), Integer::sum)))
		.forEach((k,v) -> System.out.println(k + " => " + v));
		
		//Create a map, grouping by first chart of name. The values will be the avg of the years of birthday
		people.stream()
		.collect(Collectors.groupingBy(p -> p.getName().charAt(0), Collectors.averagingInt(p -> p.getBirthday().getYear())))
		.forEach((k,v) -> System.out.println(k + " => " + v));
	}

}
