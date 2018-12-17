package com.example.java8.option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

/**
 * 
 * @author agustin.arboleya
 *
 */
public class OptionExample {

	/** The Constant FILE. */
	private static final String FILE = "src/main/resources/data.csv";
	
	/**
	 * The main method.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Optional<Person> person = Optional.empty();
		
		Person myPerson;
		if (person.isPresent()) {
		    myPerson = person.get();
		} else {
			myPerson = new Person();
		}
		
		Optional<String> priceString = Optional.of("15");
		OptionalInt priceInt = OptionalInt.of(19);
		OptionalLong pricelong = OptionalLong.of(21L);
		OptionalDouble pricedouble = OptionalDouble.of(49.95d);
		
		System.out.println("Not empty person: " + getNotEmptyPerson().orElse(new Person()));
		System.out.println("Create new person: " + getEmptyPerson().orElse(new Person()));
		System.out.println("Not person: " + getEmptyPerson().orElse(getNewPerson()));
		
		System.out.println("\n");
		try {
			System.out.println(getEmptyPerson().orElseThrow(NoSuchElementException::new));
		}catch(NoSuchElementException e) {
			System.out.println("IllegalStateException: " + e.getMessage());
		}
		System.out.print("\nNot empty person if present: ");
		getNotEmptyPerson().ifPresent(System.out::println);
		System.out.print("Empty person if present: ");
		getEmptyPerson().ifPresent(System.out::println);
		
		
		System.out.println("\n");
		List<Person> people = loadData();
		System.out.println("First person of full list: " + getFirstPersonWithName(people, "Jose"));
		System.out.println("First person of empty list: " + getFirstPersonWithName(people, "Pepe"));
		System.out.println("First person of full list with: " + getFirstPersonWithNameEx(people, "Jose"));
		System.out.println("First person of empty list: " + getFirstPersonWithNameEx(people, "Pepe"));
		
		//Compare
		Optional<String> actualItem = Optional.of("Pepe");
		Optional<String> expectedItem = Optional.of("pepe");        
		//assertEquals(expectedItem, actualItem);

		Optional<String> password = Optional.ofNullable("123456789");
		boolean v = password.filter(s -> s.length() > 8).isPresent();
		
	}
	
	private static Optional<Person> getEmptyPerson() {
		return Optional.empty();
	}
	
	private static Optional<Person> getNotEmptyPerson() {
		return Optional.ofNullable(getNewPerson());
	}
	
	private static Person getNewPerson() {
		return new Person("Elizabhet Esquinca","9903","elizabhet_esquinca@nomail.com","Tata,Porsche");
	}
	
	private static List<Person> loadData() throws IOException {
		return Files.lines(Paths.get(FILE)).map(line -> { 
			String[] splitedLine = line.split(";");
			return new Person(splitedLine[0], splitedLine[1], splitedLine[2], splitedLine[3]);
		}).collect(Collectors.toList());

	}
	
	private static List<Person> loadEmptyData() {
		return new ArrayList<>();
	}
	
	private static String getFirstPersonWithName(List<Person> list, String text) {
		return list.stream()
				.filter(p -> p.getName().contains(text))
				.findFirst()
				.map(Person::getName)
				.orElse("Not found");
	}

	private static String getFirstPersonWithNameEx(List<Person> list, String text) {
		return list.stream()
				.filter(p -> p.getName().contains(text))
				.findFirst()
				.map(Person::getName)
				.orElseThrow(NoSuchElementException::new);
	}
}
