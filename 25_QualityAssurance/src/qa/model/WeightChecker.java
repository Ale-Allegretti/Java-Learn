package qa.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import qa.persistence.BadFileFormatException;
import qa.persistence.MisureReader;
import qa.persistence.MyMisureReader;

public class WeightChecker extends Checker {

	private static NumberFormat percentFormatter = NumberFormat.getPercentInstance();

	public WeightChecker(List<Misura> elencoMisure) {
		super(elencoMisure);
		percentFormatter.setMaximumFractionDigits(2);
	}

	private boolean verifica(Misura m){
		// ritorna -1 se tutto ok, 0 se borderline, +1 se critica
		// per "critica" si intende fuori range per difetto, ossia prodotto mancante (truffa)
		// al contrario, prodotto in eccesso è sempre ok
		for(Tolleranza t: Tolleranza.values())
			if (t.getPeso()>m.getExpected()){
				//System.out.println("checking " + m.getExpected() + " vs. " + m.getReal() + ", range is " + t);
				//System.out.println("g: " + (m.getReal()-m.getExpected()) + ", " +  t.getValue());
				switch (t.getUnit()) {
					case '%': return verificaScostamentoPercentuale(m.getExpected(),m.getReal(),t.getValue());  // implicit break
					case 'g': return verificaScostamentoAssoluto(m.getExpected(),m.getReal(),t.getValue()); 					// implicit break
					default: throw new IllegalArgumentException("unexpected unit type");
				}
			} 
		return verificaScostamentoPercentuale(m.getExpected(), m.getReal(), Tolleranza.OLTRE1000.getValue());
	}

	public List<Misura> getListaMisureInRange(String descrizione){
			return getMisure(descrizione).stream().filter(m->verifica(m)).collect(Collectors.toList());
	}
	
	public double getPercentualeMisureInRange(String descrizione){
		return 100.0*getListaMisureInRange(descrizione).size()/getMisure(descrizione).size()/100;
	}
	
	public Map<String, Double> getTabellaPercentuali() {
		return getTabellaMisure().keySet().stream().collect(
				Collectors.toMap(desc->desc, desc->getPercentualeMisureInRange(desc)));
	}

	public String printTabellaPercentuali() {
		return getTabellaPercentuali().keySet().stream().collect(
				Collectors.toMap(desc->desc, desc->percentFormatter.format(getPercentualeMisureInRange(desc)))).toString();
	}

	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		try (Reader r = new FileReader("Misure.txt")) {
			MisureReader vReader = new MyMisureReader();
			List<Misura> listaMisure = vReader.leggiMisure(r);
			WeightChecker checker = new WeightChecker(listaMisure);
			System.out.println("Misure lette: " + checker.getNumeroMisure());
			for (String descrizione : checker.getTabellaMisure().keySet()) {
				System.out.println(descrizione + "(" + checker.getMisure(descrizione).size() + ")");
				System.out.println("# prodotti entro il range: " + checker.getListaMisureInRange(descrizione).size());
				System.out.println("% prodotti entro il range: " + percentFormatter.format(checker.getPercentualeMisureInRange(descrizione)));
			}
			System.out.println(checker.printTabellaPercentuali());
		}
	}
}
