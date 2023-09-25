package org.lessons.java.events;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event {
	private LocalTime time;
	private BigDecimal price;

	public Concert(String title, LocalDate date, LocalTime time, BigDecimal price, int ticketsNumber)
			throws IllegalArgumentException {
		super(title, date, ticketsNumber);
		setTime(time);
		setPrice(price);
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setPrice(BigDecimal price) throws IllegalArgumentException {
		if (price.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("Price cannot be negative.");
		else
			this.price = price;
	}

	public LocalTime getTime() {
		return time;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		final DecimalFormat decimalFormatter = new DecimalFormat("0.00");

		return "Evento: Concerto\n" + "Titolo: " + getTitle() + "\nData e ora: " + getDate().format(formatter) + ", "
				+ time + "\nBiglietti totali: " + getTicketsNumber() + "\nBiglietti venduti: " + getTicketsSold()
				+ "\nPrezzo del biglietto: " + decimalFormatter.format(price) + "€";
	}
}
