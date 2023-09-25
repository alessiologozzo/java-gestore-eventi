package org.lessons.java.events;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Show extends Event {
	private BigDecimal price;

	public Show(String title, LocalDate date, BigDecimal price, int ticketsNumber) {
		super(title, date, ticketsNumber);
		setPrice(price);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) throws IllegalArgumentException {
		if (price.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("Price cannot be negative.");
		else
			this.price = price;
	}

	@Override
	public String toString() {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		final DecimalFormat decimalFormatter = new DecimalFormat("0.00");

		return "Evento: Spettacolo\n" + "Titolo: " + getTitle() + "\nData: " + getDate().format(formatter)
				+ "\nBiglietti totali: " + getTicketsNumber() + "\nBiglietti venduti: " + getTicketsSold()
				+ "\nPrezzo del biglietto: " + decimalFormatter.format(price) + "€";
	}
}
