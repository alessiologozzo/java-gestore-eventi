package org.lessons.java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import org.lessons.java.events.Concert;
import org.lessons.java.events.Event;
import org.lessons.java.events.EventPlanner;
import org.lessons.java.events.Show;
import org.lessons.java.utils.Input;
import org.lessons.java.utils.Output;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EventPlanner eventPlanner = new EventPlanner("My Event Planner");
		int choice = -1;

		System.out.println("Benvenuto nel pianificatore di eventi!\n");
		while (choice != 0) {
			choice = Input.getInt(scanner, 0, 9,
					"Scegli un azione da eseguire:\n\n1) Mostra tutti gli eventi\n2) Cerca un evento per data\n3) Cerca un evento per indice\n4) Aggiungi un evento\n5) Prenota un evento\n6) Rimuovi un evento\n7) Rimuovi tutti gli eventi\n8) Cambia il nome del pianificatore di eventi\n9) Media dei prezzi\n0) Esci");

			switch (choice) {

			case 1:
				eventPlanner.showAll();
				break;

			case 2:
				eventPlanner.showInDate(Input.getDate(scanner, "Inserisci la data dell'evento"));
				break;

			case 3:
				eventPlanner.show(eventPlanner.size() > 0
						? Input.getInt(scanner, 0, eventPlanner.size() - 1, "Inserisci l'indice dell'evento")
						: 0);
				break;

			case 4:
				int eventType = Input.getInt(scanner, 1, 3,
						"Inserisci il tipo di evento da inserire:\n\n1) Classico (gratuito)\n2) Concerto\n3) Spettacolo");
				String title = Input.getString(scanner, "Inserisci il titolo dell'evento");
				int ticketsNumber = Input.getInt(scanner, 0, 100_000_000,
						"Inserisci il numero di biglietti disponibili");
				LocalDate date = Input.getLimitedDate(scanner, "Inserisci la data in cui si svolgerà l'evento");

				switch (eventType) {

				case 1:
					try {
						eventPlanner.add(new Event(title, date, ticketsNumber));
					} catch (IllegalArgumentException e) {
						System.err.println("Errore durante l'istanziamento della classe. Parametri non corretti.");
					}
					break;

				case 2: {
					LocalTime time = Input.getTime(scanner, "Inserisci l'orario di inizio del concerto");
					BigDecimal price = Input.getBigDecimal(scanner, "Inserisci il prezzo del biglietto");
					try {
						eventPlanner.add(new Concert(title, date, time, price, ticketsNumber));
					} catch (IllegalArgumentException e) {
						System.err.println("Errore durante l'istanziamento della classe. Parametri non corretti.");
					}
					break;
				}

				case 3:
					BigDecimal price = Input.getBigDecimal(scanner, "Inserisci il prezzo del biglietto");
					try {
						eventPlanner.add(new Show(title, date, price, ticketsNumber));
					} catch (IllegalArgumentException e) {
						System.err.println("Errore durante l'istanziamento della classe. Parametri non corretti.");
					}
				}
				break;

			case 5: {
				int index = eventPlanner.size() > 0 ? Input.getInt(scanner, 0, eventPlanner.size() - 1,
						"Inserisci l'indice dell'evento che vuoi prenotare") : -1;
				if (index != -1) {
					int innerChoice = Input.getInt(scanner, 1, 2, "1) Prenota\n2) Imposta le prenotazioni");

					switch (innerChoice) {

					case 1:
						eventPlanner.book(index);
						break;

					case 2:
						int ticketsSold = Input.getInt(scanner, 0, eventPlanner.get(index).getTicketsNumber(),
								"Inserisci il numero di biglietti venduti (max "
										+ eventPlanner.get(index).getTicketsNumber() + ")");
						eventPlanner.setBookedTickets(index, ticketsSold);
					}
				} else
					eventPlanner.printNoEventMex();

				break;
			}

			case 6:
				int index = eventPlanner.size() > 0 ? Input.getInt(scanner, 0, eventPlanner.size() - 1,
						"Inserisci l'indice dell'evento che vuoi eliminare") : -1;
				if (index != -1)
					eventPlanner.remove(index);
				else
					eventPlanner.printNoEventMex();

				break;

			case 7:
				eventPlanner.removeAll();
				break;

			case 8:
				eventPlanner.setTitle(Input.getString(scanner, "Inserisci il nuovo nome del pianificatore di eventi"));
				break;

			case 9:
				int innerChoice = Input.getInt(scanner, 1, 3,
						"Quale media vuoi ottenere?\n1) Media del prezzo di tutti gli eventi\n2) Media del prezzo di tutti i concerti\n3) Media del prezzo di tutti gli spettacoli");
				switch (innerChoice) {

				case 1:
					eventPlanner.showAvgPrice();
					break;

				case 2:
					eventPlanner.showAvgConcertPrice();
					break;

				case 3:
					eventPlanner.showAvgShowPrice();
					break;
				}
				break;
			}

		}

		Output.printGoodbye();
		scanner.close();
	}

}
