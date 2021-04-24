package entità;

import java.time.LocalDateTime;

public class IlLavoratore extends LaPersona implements Lavoratore {
	private String impiego; 
	private double stipendio;

	public IlLavoratore(String cognome, String nome, String luogoDiNascita, 
			LocalDateTime dataDiNascita, String impiego, double stipendio) {
		super(cognome, nome, luogoDiNascita, dataDiNascita);
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
		return super.toString () + ", di mestiere fa"
				+ impiego() + " e guadagna € " + stipendio();
	}

}
