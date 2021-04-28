package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaPaP extends Linea {

	public LineaPaP(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if (isCircolare())
			throw new IllegalArgumentException("Non esiste la linea selezionata");
	}

	@Override
	public Optional<Percorso> getPercorso(String fermataDa, String fermataA) {
		try {
			int a = getOrarioPassaggioAllaFermata(fermataDa);
			int b = getOrarioPassaggioAllaFermata(fermataA);
			int durata = b - a;
			
			return (durata > 0) ?  Optional.of(new Percorso(fermataDa, fermataA, this, durata)) : Optional.empty();
		}
		catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}
	

}
