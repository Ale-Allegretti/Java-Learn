package ubz.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import ubz.model.Disponibilita;
import ubz.model.Politiche;
import ubz.model.Taglio;

public class LoaderMock implements DotazioneLoader {
	int[] quantita  = { 5, 25, 30, 200, 200, 100, 100, 50, 50 };
	int[] politiche = { 5, 25, 30, 200,  10,   5,   3,  4,  3 };

	SortedMap<Taglio,Integer> mappaQuantita, mappaPolitiche; 
	
	public LoaderMock(){
		mappaQuantita = new TreeMap<>();
		mappaPolitiche = new TreeMap<>();
		for (Taglio t: Taglio.values()){
			mappaQuantita.put(t, quantita[t.ordinal()]);
			mappaPolitiche.put(t, politiche[t.ordinal()]);
		}
	}
	
	@Override
	public void load(InputStream r) throws IOException, BadFileFormatException {
		if(r==null) System.err.println("inputstream nullo");
	}

	@Override
	public Disponibilita getDisponibilita() {
		return new Disponibilita(mappaQuantita);
	}
	@Override
	public Politiche getPolitiche() {
		return new Politiche(mappaPolitiche);
	}

	// for mock pourposes only
	public SortedMap<Taglio, Integer> getMappaQuantita() {
		return mappaQuantita;
	}

	// for mock pourposes only
	public SortedMap<Taglio, Integer> getMappaPolitiche() {
		return mappaPolitiche;
	}

}
