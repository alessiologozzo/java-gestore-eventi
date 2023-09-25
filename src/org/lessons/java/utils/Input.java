package org.lessons.java.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
	public static String getString(Scanner scanner, String requestMessage) {
		String s = "";
		boolean inputSuccess = false;

		System.out.println(requestMessage);

		while (!inputSuccess) {

			s = scanner.nextLine();

			if (s.isBlank())
				System.err.println("Errore! Devi inserire una stringa valida. Riprova");
			else
				inputSuccess = true;

		}

		Output.printSeparator();

		return s.strip();
	}

	public static int getInt(Scanner scanner, int min, int max, String requestMessage) {

		String inputValue;
		int x = 0;
		boolean inputSuccess = false;

		System.out.println(requestMessage);

		while (!inputSuccess) {
			inputValue = scanner.nextLine();
			try {
				x = Integer.parseInt(inputValue);

				if (x < min)
					if (min != max)
						System.err.println("Errore! Devi inserire un numero maggiore o uguale a " + min + ". Riprova");
					else
						System.err.println("Errore! Puoi inserire solo " + min + ". Riprova");
				else if (x > max)
					if (min != max)
						System.err.println("Errore! Devi inserire un numero minore o uguale a " + max + ". Riprova");
					else
						System.err.println("Errore! Puoi inserire solo " + min + ". Riprova");
				else
					inputSuccess = true;
			} catch (NumberFormatException e) {
				System.err.println("Errore! Devi inserire un numero intero. Riprova");
			}
		}

		Output.printSeparator();

		return x;
	}

	public static LocalDate getDate(Scanner scanner, String message) {
		LocalDate date = null;
		String inputValue = "";
		boolean inputSuccess = false;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		System.out.println(message + " (dd-MM-yyyy)");

		while (!inputSuccess) {
			inputValue = scanner.nextLine();
			try {
				date = LocalDate.parse(inputValue, formatter);
				inputSuccess = true;
			} catch (DateTimeParseException e) {
				System.err.println("Errore! Devi inserire una data valida in formato dd-MM-yyyy. Riprova");
			}
		}

		Output.printSeparator();

		return date;
	}

	public static LocalDate getLimitedDate(Scanner scanner, String message) {
		LocalDate date = null;
		String inputValue = "";
		boolean inputSuccess = false;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		System.out.println(message + " (dd-MM-yyyy)");

		while (!inputSuccess) {
			inputValue = scanner.nextLine();
			try {
				date = LocalDate.parse(inputValue, formatter);

				if (date.compareTo(LocalDate.now()) <= 0)
					throw new IllegalArgumentException("Inserted date cannot be greater tha current date");

				inputSuccess = true;
			} catch (DateTimeParseException e) {
				System.err.println("Errore! Devi inserire una data valida in formato dd-mm-yyyy. Riprova");
			} catch (IllegalArgumentException e) {
				System.err
						.println("Errore! La data dell'evento deve essere postuma rispetto alla data attuale. Riprova");
			}
		}

		Output.printSeparator();

		return date;
	}

	public static LocalTime getTime(Scanner scanner, String requestMessage) {
		LocalTime time = null;
		String inputValue = "";
		boolean inputSuccess = false;
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		System.out.println(requestMessage + " (HH:mm)");
		while (!inputSuccess) {
			inputValue = scanner.nextLine();
			try {
				time = LocalTime.parse(inputValue, formatter);
				inputSuccess = true;
			} catch (DateTimeParseException e) {
				System.err.println("Errore! Devi inserire un orario valido in formato HH:mm. Riprova");
			}

		}

		Output.printSeparator();

		return time;
	}

	public static BigDecimal getBigDecimal(Scanner scanner, String requestMessage) {
		String inputValue;
		BigDecimal x = null;
		boolean inputSuccess = false;

		System.out.println(requestMessage);

		while (!inputSuccess) {
			inputValue = scanner.nextLine();
			try {
				x = BigDecimal.valueOf(Double.parseDouble(inputValue));
				if (x.compareTo(BigDecimal.ZERO) < 0)
					System.err.println("Errore! Devi inserire un numero maggiore di 0. Riprova");
				else if (x.compareTo(BigDecimal.valueOf(10_000_000)) > 0)
					System.err.println("Errore! Devi inserire un numero minore di 10.000.000. Riprova");
				else
					inputSuccess = true;
			} catch (Exception e) {
				System.err.println("Errore! Devi inserire un numero valido. Riprova");
			}
		}

		Output.printSeparator();

		return x;
	}
}
