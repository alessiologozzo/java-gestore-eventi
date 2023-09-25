package org.lessons.java.events;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
	private String title;
	private LocalDate date;
	private int ticketsNumber, ticketsSold;

	public Event(String title, LocalDate date, int ticketsNumber) throws IllegalArgumentException {
		setDate(date);
		setTicketsNumber(ticketsNumber);
		setTicketsSold(0);
		setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getTicketsNumber() {
		return ticketsNumber;
	}

	public int getTicketsSold() {
		return ticketsSold;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(LocalDate date) throws IllegalArgumentException {
		LocalDate currentDate = LocalDate.now();
		if (currentDate.compareTo(date) >= 0)
			throw new IllegalArgumentException("Current date cannot be equal or greater than event date.");
		else
			this.date = date;
	}

	public void setTicketsNumber(int ticketsNumber) {
		if (ticketsNumber < 0)
			throw new IllegalArgumentException("The number of tickets must be positive.");
		else if (ticketsNumber < ticketsSold)
			throw new IllegalArgumentException(
					"The number of total tickets cannot be less than the number of tickets sold.");
		else
			this.ticketsNumber = ticketsNumber;
	}

	public void setTicketsSold(int ticketsSold) {
		if (ticketsSold < 0)
			throw new IllegalArgumentException("The number of tickets sold must be positive.");
		else if (ticketsSold > ticketsNumber)
			throw new IllegalArgumentException("The number of tickets sold cannot exceed the number of total tickets.");
		else
			this.ticketsSold = ticketsSold;
	}

	public void book() throws IllegalArgumentException {
		setTicketsSold(ticketsSold + 1);
	}

	public void cancelBooking() throws IllegalArgumentException {
		setTicketsSold(ticketsSold - 1);
	}

	@Override
	public String toString() {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		return "Evento: Classico\nTitolo: " + title + "\nData: " + date.format(formatter) + "\nBiglietti totali: "
				+ ticketsNumber + "\nBiglietti venduti: " + ticketsSold + "\nPrezzo: Gratuito";
	}
}
