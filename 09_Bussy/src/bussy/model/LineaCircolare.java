package bussy.model;

import java.util.Map;
import java.util.Optional;

public class LineaCircolare extends Linea {

	public LineaCircolare(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if (!isCircolare())
			throw new IllegalArgumentException("Non linea non è circolare");
	}

	@Override
	public Optional<Percorso> getPercorso(String FermataDa, String FermataA) {
		try {
			int durata = 0;
			String capolineaI = this.getCapolineaIniziale().getValue().getNome();
			String capolineaF = this.getCapolineaFinale().getValue().getNome();
			int durataI = getOrarioPassaggioAllaFermata(FermataDa);
			int durataF = getOrarioPassaggioAllaFermata(FermataA);
			int giroCompleto = this.getCapolineaFinale().getKey();
			
			if(capolineaI.equals(FermataDa) && !capolineaF.equals(FermataA)) 
				durata = durataF;
			if(!capolineaI.equals(FermataDa) && capolineaF.equals(FermataA))
				durata = durataF - durataI;
			if(FermataDa.equals(FermataA))
				durata = durataF;
			if(durataI < durataF)
				durata = durataF - durataI;
			if(durataI >= durataF)
				durata = giroCompleto - (durataI - durataF);
			
			return Optional.of(new Percorso(FermataDa, FermataA, this, durata));
		}	
		catch (IllegalArgumentException e){
			return Optional.empty();
		}
	}
	
	

}
