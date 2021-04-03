package ticketsosta;

import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Ticket {
	
	private LocalTime inizio, fine;
	private double costo;
	
	public Ticket (LocalTime inizio, LocalTime fine, double costo) {
		this.inizio = inizio;
		this.fine = fine;
		this.costo = costo;		
	}

	public LocalTime getInizio() {
		return inizio;
	}

	public LocalTime getFine() {
		return fine;
	}

	public double getCosto() {
		return costo;
	}
	
	@Override
	public String toString() {
		return "Sosta autorizzata\ndalle " +
				inizio.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) + 
				" alle " +
				fine.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) +
				"\n" +
				"Durata totale: " + toStringDuration(Duration.between(inizio, fine)) +
				"\n" +
				"Totale pagato: " + getCostoAsString();
	}
	
	private String toStringDuration(Duration duration) {
		int minuti = duration.toMinutesPart();
		String sMinuti = (minuti < 10 ? "0" : "") + minuti;  //se vera stampa "04 minuti", se falsa "10 minuti"
		return duration.toHours() + ":" + sMinuti;
	}

	public String getCostoAsString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return formatter.format(costo);		// formatta il costo (double) secondo la notazione italiana
	}
	
	

}
