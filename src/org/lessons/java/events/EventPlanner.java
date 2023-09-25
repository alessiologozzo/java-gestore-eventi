package org.lessons.java.events;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.lessons.java.utils.Output;

public class EventPlanner {
	private String title;
	private List<Event> events;

	public EventPlanner(String title) {
		this.title = title;
		events = new ArrayList<>();
	}

	public void add(Event event) {
		events.add(event);
	}

	public void remove(int index) {
		events.remove(index);
	}

	public void removeAll() {
		if (events.size() > 0)
			events = new ArrayList<>();
	}

	public Event get(int index) {
		return events.get(index);
	}

	public List<Event> getAll() {
		return events;
	}

	private List<Boolean> getInDate(LocalDate date) {
		List<Boolean> x = new ArrayList<>();

		events.stream().forEach((event) -> {
			if (event.getDate().compareTo(date) == 0)
				x.add(true);
			else
				x.add(false);
		});

		return x;
	}

	public void show(int index) throws ArrayIndexOutOfBoundsException {
		String s = Output.CYAN + title + "\n\n" + Output.RESET;

		if (size() < 1)
			s += "\nNon ci sono eventi pianificati. Aggiungine uno e riprova!";
		else if (index >= events.size() || index < 0)
			throw new ArrayIndexOutOfBoundsException("Index is out of bounds.");
		else
			s += "I°" + index + "\n" + get(index).toString();

		System.out.println(s);
		Output.printSeparator();
	}

	public void showAll() {
		String s = Output.CYAN + title + "\n\n" + Output.RESET;

		if (events.size() > 0)
			for (int i = 0; i < events.size(); i++) {
				s += "I°" + i + "\n" + events.get(i).toString();

				if (i + 1 < events.size())
					s += "\n\n";
			}
		else
			s += "\nNon ci sono eventi pianificati. Aggiungine uno e riprova!";

		System.out.println(s);
		Output.printSeparator();
	}

	public void showInDate(LocalDate date) {
		String s = Output.CYAN + title + "\n\n" + Output.RESET;
		List<Boolean> booleanEvents = getInDate(date);

		for (int i = 0; i < events.size(); i++)
			if (booleanEvents.get(i)) {
				s += "I° " + i + "\n" + events.get(i).toString();

				if (i + 1 < events.size())
					s += "\n\n";
			}

		System.out.println(s);
		Output.printSeparator();
	}

	public int size() {
		return events.size();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void book(int index) {
		if (events.get(index).getTicketsSold() + 1 <= events.get(index).getTicketsNumber())
			events.get(index).book();
		else
			System.out.println(Output.YELLOW + "L'evento è sold out. Impossibile prenotare." + Output.RESET);

		Output.printSeparator();
	}

	public void setBookedTickets(int index, int ticketsSold) {
		events.get(index).setTicketsSold(ticketsSold);
	}

	public void printNoEventMex() {
		String s = Output.CYAN + getTitle() + "\n\n" + Output.RESET;
		s += "\nNon ci sono eventi pianificati. Aggiungine uno e riprova!";

		Output.printSeparator();
		System.out.println(s);
		Output.printSeparator();
	}

	public void showAvgPrice() {
		String s = Output.CYAN + title + "\n\n" + Output.RESET;
		BigDecimal avg = BigDecimal.ZERO;
		final DecimalFormat decimalFormatter = new DecimalFormat("0.00");

		if (events.size() >= 1) {

			for (Event event : events)
				if (event instanceof Concert) {
					Concert concert = (Concert) event;
					avg = avg.add(concert.getPrice());
				} else if (event instanceof Show) {
					Show show = (Show) event;
					avg = avg.add(show.getPrice());
				}

			avg = avg.divide(new BigDecimal(events.size()), 5, RoundingMode.HALF_UP);

			System.out.println(s + "La media dei prezzi di tutti gli eventi è " + decimalFormatter.format(avg) + "€");
			Output.printSeparator();
		} else {
			s += "\nNon ci sono eventi pianificati. Aggiungine uno e riprova!";
			System.out.println(s);
			Output.printSeparator();
		}
	}

	public void showAvgConcertPrice() {
		String s = Output.CYAN + title + "\n\n" + Output.RESET;
		BigDecimal avg = BigDecimal.ZERO;
		final DecimalFormat decimalFormatter = new DecimalFormat("0.00");
		int counter = 0;

		if (events.size() >= 1) {

			for (Event event : events)
				if (event instanceof Concert) {
					Concert concert = (Concert) event;
					avg = avg.add(concert.getPrice());
					counter++;
				}

			if (counter > 0) {
				avg = avg.divide(new BigDecimal(counter), 5, RoundingMode.HALF_UP);
				System.out.println(s + "La media dei prezzi dei concerti è " + decimalFormatter.format(avg) + "€");
				Output.printSeparator();
			} else {
				s += "\nNon ci sono concerti pianificati. Aggiungine uno e riprova!";
				System.out.println(s);
				Output.printSeparator();
			}
		} else {
			s += "\nNon ci sono eventi pianificati. Aggiungine uno e riprova!";
			System.out.println(s);
			Output.printSeparator();
		}
	}

	public void showAvgShowPrice() {
		String s = Output.CYAN + title + "\n\n" + Output.RESET;
		BigDecimal avg = BigDecimal.ZERO;
		final DecimalFormat decimalFormatter = new DecimalFormat("0.00");
		int counter = 0;

		if (events.size() >= 1) {

			for (Event event : events)
				if (event instanceof Show) {
					Show show = (Show) event;
					avg = avg.add(show.getPrice());
					counter++;
				}

			if (counter > 0) {
				avg = avg.divide(new BigDecimal(counter), 5, RoundingMode.HALF_UP);
				System.out.println(s + "La media dei prezzi degli spettacoli è " + decimalFormatter.format(avg) + "€");
				Output.printSeparator();
			} else {
				s += "\nNon ci sono spettacoli pianificati. Aggiungine uno e riprova!";
				System.out.println(s);
				Output.printSeparator();
			}
		} else {
			s += "\nNon ci sono eventi pianificati. Aggiungine uno e riprova!";
			System.out.println(s);
			Output.printSeparator();
		}
	}
}
