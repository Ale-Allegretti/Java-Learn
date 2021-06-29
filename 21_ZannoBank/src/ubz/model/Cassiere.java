package ubz.model;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import ubz.persistence.DotazioneLoader;

public class Cassiere {

	private Disponibilita disponibilita;

	public Cassiere(DotazioneLoader loader) {
		if (loader == null)
			throw new IllegalArgumentException("loader nullo");
		this.disponibilita = loader.getDisponibilita();
	}

	public int getDisponibilitaTaglio(Taglio t){
		return disponibilita.getQuantita(t);
	} 
	
	public Prelievo preleva(int importo) throws ImpossibleWithdrawException{
		SortedMap<Taglio, Integer> mappaPrelievo = new TreeMap<>();
		int importoResiduo = importo;
		for(Taglio t: Taglio.values()){
			 int quantitaTaglioCorrente = calcolaQuantitaDiQuestoTaglio(t, importoResiduo);
			 importoResiduo -= quantitaTaglioCorrente * t.getValore();
			 mappaPrelievo.put(t, quantitaTaglioCorrente);
		}
		if (importoResiduo>0) 
			throw new ImpossibleWithdrawException(importo, importoResiduo);
		else {
			aggiornaDisponibilitaInCassa(mappaPrelievo);
			return new Prelievo(mappaPrelievo);
		}
	}

	protected void aggiornaDisponibilitaInCassa(Map<Taglio, Integer> mappaPrelievo) {
		for(Taglio t: Taglio.values()){
			int disponibilitaAttuale = getDisponibilitaTaglio(t);
			int banconoteDaPrelevare = mappaPrelievo.get(t);
			disponibilita.aggiorna(t, disponibilitaAttuale-banconoteDaPrelevare);
		}	
	}

	/* versione base SENZA POLITICHE */
	protected int calcolaQuantitaDiQuestoTaglio(Taglio t, int importo){
		 int quantitaRichiesta = importo/t.getValore();
		 int quantitaDisponibile = disponibilita.getQuantita(t);
		 return Math.min(quantitaRichiesta, quantitaDisponibile);
	}
}
