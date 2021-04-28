package bussy.model;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCercatore implements Cercatore {
	
	private Map<String, Linea> mappaLinee;

	public MyCercatore(Map<String, Linea> mappaLinee) {
		if (mappaLinee == null || mappaLinee.isEmpty())
			throw new IllegalArgumentException("Mappa linee nulla o vuota");
		this.mappaLinee = mappaLinee;
	}

	@Override
	public SortedSet<Percorso> cercaPercorsi(String fermataDa, String fermataA, OptionalInt durataMax) {
		if(fermataDa == null || fermataA == null)
			throw new IllegalArgumentException("Una delle fermate non valida");
		
		SortedSet<Percorso> percorsiDiretti = new TreeSet<Percorso>();
		for (Linea linea : mappaLinee.values()) {
			Optional<Percorso> percOptional = linea.getPercorso(fermataDa, fermataA);
			if (percOptional.isPresent()) {
				Percorso percorso = percOptional.get();
				if (percorso.getDurata() > 0)
					percorsiDiretti.add(percorso);
			}
		}
		
		
		SortedSet<Percorso> percorsiDirettiNonPiuLunghiDiX;
		if (durataMax.isPresent()) {
			percorsiDirettiNonPiuLunghiDiX = new TreeSet<Percorso>();
			for (Percorso percorso : percorsiDiretti) {
				if (percorso.getDurata() <= durataMax.getAsInt())
					percorsiDirettiNonPiuLunghiDiX.add(percorso);
			}
		}
		else 
			percorsiDirettiNonPiuLunghiDiX = percorsiDiretti;
		
		return percorsiDirettiNonPiuLunghiDiX;
	}

	
	@Override
	public Map<String, Linea> getMappaLinee() {
		return mappaLinee;
	}

}
