package zannopoll.model;

import java.text.NumberFormat;
import java.util.*;

import java.util.stream.Collectors;

public class SondaggioPercentuale extends Sondaggio{

	private Map<String,Double> mappaPercentuali; 
	
	public SondaggioPercentuale(List<Intervista> listaInterviste) {
		super(listaInterviste);
		mappaPercentuali = new HashMap<>();
		String nomeIntervista;
		for (Intervista intervista : listaInterviste) {
			nomeIntervista = intervista.getNome();
			mappaPercentuali.put(nomeIntervista, 
					((double) this.getListaIntervistati(nomeIntervista).size()) / this.getTotaleIntervistati());
		}
	}
	
	public double getPercentualeIntervistati(String scelta) {
		if (scelta == null || scelta.isEmpty())
			throw new IllegalArgumentException("scelta nulla o vuota");
		if (mappaPercentuali.get(scelta) == null)
			throw new IllegalArgumentException("scelta non valida");
		
		return mappaPercentuali.get(scelta);
	}
	
	public List<Intervista> getIntervisteFiltrate(int minAge, int maxAge, Optional<Sesso> maybeSex) {
		return maybeSex.isPresent() ? 
				super.getListaInterviste().stream()
				.filter(intervista -> intervista.getSesso().compareTo(maybeSex.get()) == 0 &&
						intervista.getEta() >= minAge && intervista.getEta() <= maxAge)
				.collect(Collectors.toList()) : 
				super.getListaInterviste().stream()
				.filter(intervista -> intervista.getEta() >= minAge && intervista.getEta() <= maxAge)
				.collect(Collectors.toList());					
	}
	
	public Optional<SondaggioPercentuale> getSondaggioFiltrato(int minAge, int maxAge, Optional<Sesso> maybeSex) {
		List<Intervista> intervistaFiltrata = getIntervisteFiltrate(minAge, maxAge, maybeSex);
		if (intervistaFiltrata.isEmpty())
			return Optional.empty();
		else 
			return Optional.of(new SondaggioPercentuale(intervistaFiltrata));
	}
	
	public Map<String, Double> getMappaPercentuali() {
		return mappaPercentuali;
	}
	
	public String toString() {
		NumberFormat formatter = NumberFormat.getPercentInstance();
		formatter.setMaximumFractionDigits(2);
		StringBuilder sb = new StringBuilder();
		for (String scelta : this.getMappaPercentuali().keySet()) {
			sb.append(scelta + "\t" + formatter.format(this.getMappaPercentuali().get(scelta)) + "\n");
		}
		return sb.toString();
	}
	
	
	
	
	
	
}
