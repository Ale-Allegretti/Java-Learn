package teethferries.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Servizio {

	private String nave;
	private Tratta tratta;
	private LocalTime orarioPartenza;
	private LocalTime orarioArrivo;
	private double costo;
	
	public Servizio(String nave, Tratta tratta, LocalTime orarioPartenza, LocalTime orarioArrivo, double costo) {
		if (nave == null)
			throw new IllegalArgumentException("nave");
		if (tratta == null)
			throw new IllegalArgumentException("tratta");
		if (orarioPartenza == null)
			throw new IllegalArgumentException("orarioPartenza");
		if (orarioArrivo == null)
			throw new IllegalArgumentException("orarioArrivo");
		if (costo <= 0)
			throw new IllegalArgumentException("costo");
		
		this.nave = nave;
		this.tratta = tratta;
		this.orarioPartenza = orarioPartenza;
		this.orarioArrivo = orarioArrivo;
		this.costo = costo;
	}

	public String getNave() {
		return nave;
	}

	public Tratta getTratta() {
		return tratta;
	}

	public LocalTime getOrarioPartenza() {
		return orarioPartenza;
	}

	public LocalTime getOrarioArrivo() {
		return orarioArrivo;
	}

	public double getCosto() {
		return costo;
	}
	
	public Duration getDurata() {
		return Duration.between(getOrarioPartenza(), getOrarioArrivo());
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
		builder.append(this.nave + " da ");
		builder.append(this.tratta.getPortoPartenza().toString() + " a ");
		builder.append(this.tratta.getPortoArrivo().toString() + " ");
		builder.append(formatter.format(getOrarioPartenza()) + "-");
		builder.append(formatter.format(getOrarioArrivo()));
		
		return builder.toString();
	}
	
	
	
	
	
}
