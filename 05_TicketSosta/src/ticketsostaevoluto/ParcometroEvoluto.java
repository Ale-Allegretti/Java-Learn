package ticketsostaevoluto;

import java.time.*;
import java.util.Arrays;

import ticketsosta.Tariffa;

public class ParcometroEvoluto {
	
	private Tariffa[] tariffa;
	
	public ParcometroEvoluto(Tariffa[] tariffa) {
		this.tariffa = new Tariffa[7]; 
		this.tariffa = Arrays.copyOf(tariffa,7);
	}
	
	public TicketEvoluto emettiTicket(LocalDateTime inizio, LocalDateTime fine) {
		DayOfWeek giornoInizio = inizio.getDayOfWeek();
		int i = 1; 
		while (giornoInizio.equals(DayOfWeek.of(i)) == false && i < 8)  //itero fino a trovare l'indice del giorno corrispondente
			 i++;
		int k = i - 1;		//cambio indice perchè tarriffa[] parte da 0
		
		LocalDateTime inizioEffettivo = inizio.plusMinutes(this.tariffa[k].getMinutiFranchigia());
		double costo;
		long durataSosta = Duration.between(inizioEffettivo, fine).toMinutes();
		if (durataSosta < this.tariffa[k].getDurataMinima())
			costo = this.tariffa[k].getDurataMinima() * this.tariffa[k].getTariffaOraria() / 60.0; 
		else																				 
			costo = calcolaCostoSuPiuGiorni(inizioEffettivo, fine);
		
		return new TicketEvoluto(inizio, fine, costo);  
	}											 
	
	private double calcolaCosto(double costoOrario, LocalTime da, LocalTime a) {
		Duration durataSosta;
		if(a.isBefore(da) || LocalTime.of(0, 0).equals(a))
			durataSosta = Duration.between(da, LocalTime.of(23, 59)).plusMinutes(1);  // **vedi giù
		else 
			durataSosta = Duration.between(da, a);
		return costoOrario * durataSosta.toMinutes() / 60.0;	
	}															
	
	private double calcolaCostoSuPiuGiorni(LocalDateTime inizio, LocalDateTime fine) {
		double costoOrarioGiorno = 0.0;
		DayOfWeek giornoFine = fine.getDayOfWeek();
		DayOfWeek giornoInizio = inizio.getDayOfWeek();
		DayOfWeek giornoPrima;
		int i = 1; 
		while (giornoFine.equals(DayOfWeek.of(i)) == false && i < 8)
			 i++;
		int j = i - 1;		//cambio indice perchè tarriffa[] parte da 0
		
		if (!giornoFine.equals(giornoInizio)) {
			costoOrarioGiorno = calcolaCosto(this.tariffa[j].getTariffaOraria(), LocalTime.of(0, 0), fine.toLocalTime());
			int k = 1;
			do {
				i = 1;
				giornoPrima = fine.getDayOfWeek().minus(k);
				while (giornoPrima.equals(DayOfWeek.of(i)) == false && i < 8)
					i++;
				int y = i - 1;
				if (!giornoPrima.equals(giornoInizio)) {
					costoOrarioGiorno = costoOrarioGiorno + calcolaCosto(this.tariffa[y].getTariffaOraria(), LocalTime.of(0, 0), LocalTime.of(0, 0));
					k++;												//da mezzanotte a mezzanotte è valida grazie all'impostazione di calcolaCosto**
				}														
				else
					costoOrarioGiorno = costoOrarioGiorno + calcolaCosto(this.tariffa[y].getTariffaOraria(), inizio.toLocalTime(), LocalTime.of(0, 0));
			} while(!giornoPrima.equals(giornoInizio));
		}
		
		else
			costoOrarioGiorno = calcolaCosto(this.tariffa[j].getTariffaOraria(), inizio.toLocalTime(), fine.toLocalTime());
		
		return costoOrarioGiorno;
	}
	
	@Override 
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parcometro configurato con le tariffe:  ");
		builder.append(System.lineSeparator());
		
		for(Tariffa tari : tariffa)
		{
			builder.append(tari);
			builder.append(System.lineSeparator());
		}
		
		return builder.toString();
	}

}
