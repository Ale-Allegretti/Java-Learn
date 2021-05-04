package zannotaxi.model;

import java.time.LocalTime;
import java.util.Optional;

public class Tassametro implements ITassametro {
	
	private FasciaOraria[] fasceOrarie;
	private ITariffaTaxi[] tariffe;

	public Tassametro(ITariffaTaxi[] tariffe, FasciaOraria[] fasceOrarie) {
		this.fasceOrarie = fasceOrarie;
		this.tariffe = tariffe;
	}
	
	
	private double getScattoIniziale (LocalTime oraInizioCorsa) {
		for (int i = 0; i < this.fasceOrarie.length; i++)
			if (this.fasceOrarie[i].contiene(oraInizioCorsa))
				return this.fasceOrarie[i].getCostoScattoIniziale();
			
		return Double.NaN;
	}
	
	
	private Optional<Scatto> findScatto (double spazioPercorsoDaUltimoScatto, int tempoTrascorsoDaUltimoScatto, 
										double costoCorrente) {
		for (int i = 0; i < this.tariffe.length; i++) {
			if (!this.tariffe[i].getScattoCorrente(tempoTrascorsoDaUltimoScatto, spazioPercorsoDaUltimoScatto, costoCorrente).isEmpty())
				return this.tariffe[i].getScattoCorrente(tempoTrascorsoDaUltimoScatto, spazioPercorsoDaUltimoScatto, costoCorrente);
		}
		return Optional.empty();
	}

	
	@Override
	public double calcolaCostoCorsa(CorsaTaxi corsa) {
		double cv = 0;
		int t = 0;
		double s = 0;
		
		for (int i = 1; i < corsa.getRilevazioniDistanze().length; i++) {
			s += corsa.getRilevazioniDistanze()[i] - corsa.getRilevazioniDistanze()[i-1];
			t += 1;
			Optional<Scatto> scatto = Optional.of(new Scatto(t, s, i));
			if(!findScatto(s, t, cv).isEmpty()) {
				scatto = findScatto(s, t, cv);
				cv += scatto.get().getCosto();
				s = s - scatto.get().getSpazio();
				t = t - scatto.get().getTempo();
			}
		}
		cv += getScattoIniziale(corsa.getOraPartenza());
		
		return cv;
	}

}
