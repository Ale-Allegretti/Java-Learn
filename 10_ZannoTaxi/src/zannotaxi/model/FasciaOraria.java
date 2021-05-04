package zannotaxi.model;

import java.time.LocalTime;

public class FasciaOraria {
	
	private double costoScattoIniziale;
	private LocalTime inizio;
	private LocalTime fine;
	
	public FasciaOraria(LocalTime inizio, LocalTime fine, double costoScattoIniziale) {
		this.costoScattoIniziale = costoScattoIniziale;
		this.inizio = inizio;
		this.fine = fine;
	}
	
	public boolean contiene (LocalTime time) {
		return (time.equals(this.inizio) ||
				time.isAfter(this.inizio) || time.isBefore(this.fine));
	}

	public double getCostoScattoIniziale() {
		if (this.contiene(inizio))
			return costoScattoIniziale;
		else return 0.0;
	}
	
	
	
	

}
