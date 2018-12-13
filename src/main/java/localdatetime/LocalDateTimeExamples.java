package localdatetime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import com.example.java8.model.Person;

public class LocalDateTimeExamples {
	/** The Constant FILE. */
	private static final String FILE = "src/main/resources/data.csv";

	public static void main(String[] args) throws IOException {
		
		List<Person> people = Files.lines(Paths.get(FILE)).map(line -> { 
			String[] splitedLine = line.split(";");
			return new Person(splitedLine[0], splitedLine[1], splitedLine[2], splitedLine[3]);
		}).collect(Collectors.toList());

		System.out.println("Times");
		LocalTime timeNow = LocalTime.now();
		System.out.println("Now: " + timeNow);
		LocalTime timeNowLondon = LocalTime.now(ZoneId.of("Europe/London"));
		System.out.println("Now london: " + timeNowLondon);
		LocalTime myTime = LocalTime.of(18, 32, 59);
		System.out.println("My time: " + myTime);
		LocalTime myTime2 = LocalTime.of(18, 32, 59, 4);
		System.out.println("My time 2: " + myTime2);

		System.out.println("\nDates");
		LocalDate dateNow = LocalDate.now();
		System.out.println("Now: " + dateNow);
		LocalDate dateNowLondon = LocalDate.now(ZoneId.of("Europe/Madrid"));
		System.out.println("Now London: " + dateNowLondon);
		LocalDate dateNowMajuro = LocalDate.now(ZoneId.of("Pacific/Majuro"));
		System.out.println("Now Majuro: " + dateNowMajuro);
		LocalDate myDate = LocalDate.of(1984, 8, 10);
		System.out.println("My date: " + myDate);
		
		System.out.println("\nPeriod");
		Period periodo = Period.between(myDate, dateNow);
		System.out.println("Periodo " + myDate + " <-> " + dateNow + ": " + periodo + " " + periodo.getYears());
		Period periodo2 = Period.between(dateNow, myDate);
		System.out.println("Periodo " + dateNow + " <-> " + myDate + ": " + periodo2 + " " + periodo2.getYears());
		
		
		
	}
}
