package com.example.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

public class StreamReduce {

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

		// Sum total cars of people (lambda)
	  	people.stream().map(Person::getCars).map(List::size).reduce((x,y) -> x + y).ifPresent(System.out::println);
	  	// Sum total cars of people (method reference)
	  	people.stream().map(Person::getCars).map(List::size).reduce(Integer::sum).ifPresent(System.out::println);
	  	
	  	// Reduce a list
	  	people.stream().filter(p -> p.getBirthday().isAfter(LocalDate.of(2000, Month.DECEMBER, 25)))
		  		.map(Person::getMail).reduce((x, y) -> String.join(", ", x, y)).ifPresent(System.out::println);
	  	
	  	// Use identity with accumulator
	  	System.out.println(people.stream().filter(p -> p.getBirthday().isAfter(LocalDate.of(2000, Month.DECEMBER, 25)))
		  		.map(Person::getMail).reduce("Mails:", (x, y) -> String.join(" ", x, y)));
	  	
	  	List<Integer> ints = Arrays.asList(2,3,4);
	  	
	  	// funcion, primero suma, y despues multiplica los resultados
	  	Integer init = 2;
	  	List<Integer> sum = new ArrayList<>();
	  	Integer result = 1;
	  	for (Integer i: ints)
	  		sum.add(init + i);
	  	for (Integer i: sum)
	  		result *= i;	  	
	  	System.out.println(result);
	  		
	  	System.out.println(ints.parallelStream().reduce(2, (x1, x2) -> x1 + x2, (x, y) -> x * y));
  	 
	  	
	  	
	}

}
