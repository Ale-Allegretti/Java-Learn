package ticketsosta;

import java.time.*;

public class Parcometro {
	
	private Tariffa tariffa;
	
	public Parcometro(Tariffa tariffa) {
		this.tariffa = tariffa;
	}
	
	public Ticket emettiTicket(LocalTime inizio, LocalTime fine) {
		LocalTime inizioEffettivo = inizio.plusMinutes(this.tariffa.getMinutiFranchigia());
		double costo;
		long durataSosta = Duration.between(inizioEffettivo, fine).toMinutes();
		if (durataSosta < this.tariffa.getDurataMinima())
			costo = this.tariffa.getDurataMinima() * this.tariffa.getTariffaOraria() / 60.0;  //perchè pagamento è all'ora
		else																				  //ma i calcoli sono fatti sui minuti
			costo = calcolaCosto(this.tariffa.getTariffaOraria(), inizioEffettivo, fine);
		
		return new Ticket(inizio, fine, costo);   //si inserisce inizio perchè Ticket dovrà stampare comunque
	}											  //il biglietto segnando l'ora iniziale (senza franchigia)
	
	private double calcolaCosto(double costoOrario, LocalTime da, LocalTime a) {
		Duration durataSosta;
		if(a.isBefore(da) || LocalTime.of(0, 0).equals(a))
			durataSosta = Duration.between(da, LocalTime.of(23, 59)).plusMinutes(1);
		else 
			durataSosta = Duration.between(da, a);
		return costoOrario * durataSosta.toMinutes() / 60.0;	//perchè pagamento è all'ora
	}															//ma i calcoli sono fatti sui minuti
	
	@Override 
	public String toString() {
		return "Parcometro configurato con la tariffa " + tariffa.toString();
	}

}
