package com.example.java8.localdatetime;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * The Class LocalDateTimeExamples.
 */
public class LocalDateTimeExamples {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Times");
		LocalTime timeNow = LocalTime.now();
		System.out.println("Now: " + timeNow);
		LocalTime timeNowLondon = LocalTime.now(ZoneId.of("Europe/London"));
		System.out.println("Now london: " + timeNowLondon);
		LocalTime myTime = LocalTime.of(18, 32, 59);
		System.out.println("My time: " + myTime);
		LocalTime myTime2 = LocalTime.of(18, 32, 59, 457);
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
		LocalDate myDate2 = LocalDate.of(1999, Month.DECEMBER, 31);
		System.out.println("My date: " + myDate2);
		LocalDate myDate3 = myDate2.withYear(2018);
		System.out.println("My date: " + myDate3);
		
		System.out.println("\nPeriod");
		Period periodo = Period.between(myDate, dateNow);
		System.out.println("Period " + myDate + " <-> " + dateNow + ": " + periodo + " (" + periodo.getYears() + " years)");
		Period periodo2 = Period.between(dateNow, myDate);
		System.out.println("Period " + dateNow + " <-> " + myDate + ": " + periodo2 + " (" + periodo2.getYears() + " years)");
		Period periodo3 = dateNow.until(myDate);
		System.out.println("Period " + dateNow + " <-> " + myDate + ": " + periodo3 + " (" + periodo3.getYears() + " years)");

		System.out.println("\nDuration");
		Duration duration = Duration.between(timeNow, myTime);
		System.out.println("Duration " + myTime + " <-> " + timeNow + ": " + duration + " (" + duration.getSeconds() + " seconds)");

		System.out.println("\nChronoUnit");
		Long d = ChronoUnit.DECADES.between(LocalDate.of(1900, Month.JANUARY, 1), LocalDate.of(2019, Month.JANUARY, 1));
		System.out.println("ChronoUnit decades: " + d);
		Long m = ChronoUnit.MINUTES.between(LocalTime.of(12, 30, 0), LocalTime.of(13, 31, 10));
		System.out.println("ChronoUnit minutes: " + m);
		Long h = ChronoUnit.HOURS.between(LocalDateTime.of(1900, Month.JANUARY, 1, 10, 0, 0), LocalDateTime.of(2019, Month.JANUARY, 1, 10, 0, 0));
		System.out.println("ChronoUnit hours: " + h);
		
		System.out.println("\nOperations");
		System.out.println(myDate + " is after now?: " + myDate.isAfter(dateNow));
		System.out.println("2 months after " + myDate + " is " + myDate.plusMonths(2));
		System.out.println("1 week after " + myDate + " is " + myDate.minusWeeks(1));
		System.out.println("30 days before " + myDate + " is " + myDate.minusDays(30));
		System.out.println("30 days before " + myDate + " is " + myDate.minus(Period.ofDays(30)));
		System.out.println("30 days before " + myDate + " is " + myDate.minus(30, ChronoUnit.DAYS));
		
		System.out.println("\nRanges");
		System.out.println("Range day of month: " + myDate.range(ChronoField.DAY_OF_MONTH));
		System.out.println("Range day of week: " + myDate.range(ChronoField.DAY_OF_WEEK));
		
		System.out.println("\nFunctions");
		System.out.println(myDate.atStartOfDay());
		System.out.println(myDate2.atTime(myTime2));
		System.out.println("Leap year: " + myDate.isLeapYear());
		System.out.println("Month size " + myDate.getMonth() + ": " + myDate.lengthOfMonth());
		
		LocalDateTime myDateTime = LocalDateTime.now();
		System.out.println("\nFormats");
		System.out.println(myDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.println(myDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
		System.out.println(myDateTime.format(DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy")));
		System.out.println(myDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a")));
		System.out.println(myDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:n")));
		
	}
}
