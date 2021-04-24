package entità;

import java.time.LocalDateTime;

public class LoStudenteLavoratore extends LoStudente implements StudenteLavoratore {
	private String impiego;
	private double stipendio;

	public LoStudenteLavoratore(String cognome, String nome, String luogoDiNascita, LocalDateTime dataDiNascita,
			int matricola, String impiego, double stipendio) {
		super(cognome, nome, luogoDiNascita, dataDiNascita, matricola);
		this.impiego = impiego; 
		this.stipendio = stipendio;
	}

	@Override
	public String impiego() {
		return impiego;
	}
	
	@Override
	public double stipendio() {
		return stipendio;
	}
	
	@Override 
	public String toString () { 
		return super.toString () + ", di mestiere fa il " + impiego() + " e guadagna € " + stipendio();
	}

}
