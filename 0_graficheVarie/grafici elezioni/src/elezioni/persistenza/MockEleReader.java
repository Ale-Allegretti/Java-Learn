package elezioni.persistenza;

import java.io.Reader;

import elezioni.model.Coppia;
import elezioni.model.Risultato;

public class MockEleReader implements EleReader {
	
	public Risultato readAll(Reader rdr) {
		Risultato risultato = new Risultato();
		// fake
		// FERRARA			CDX 36.629,	CSX 24.009,	M5S  5.161,	altri 9.523
		// MODENA			CDX 30.178,	CSX 50.750,	M5S  9.033,	altri 5.037
		// REGGIO EMILIA	CDX 23.116,	CSX 40.243,	M5S 12.049,	altri 6.498
		// FORLI'			CDX 27.905,	CSX 22.676,	M5S  6.594,	altri 3.758
		risultato.add("Ferrara", new Coppia("CDX", 36629));
		risultato.add("Ferrara", new Coppia("CSX", 24009));
		risultato.add("Ferrara", new Coppia("M5S", 5161));
		risultato.add("Ferrara", new Coppia("altri", 9523));
		risultato.add("Modena", new Coppia("CDX", 30178));
		risultato.add("Modena", new Coppia("CSX", 50750));
		risultato.add("Modena", new Coppia("M5S", 9033));
		risultato.add("Modena", new Coppia("altri", 5037));
		risultato.add("Reggio", new Coppia("CDX", 23116));
		risultato.add("Reggio", new Coppia("CSX", 40243));
		risultato.add("Reggio", new Coppia("M5S", 12049));
		risultato.add("Reggio", new Coppia("altri", 6498));
		risultato.add("Forlì", new Coppia("CDX", 27905));
		risultato.add("Forlì", new Coppia("CSX", 22676));
		risultato.add("Forlì", new Coppia("M5S", 6594));
		risultato.add("Forlì", new Coppia("altri", 3758));
		return risultato; // fake
	}
	
}
