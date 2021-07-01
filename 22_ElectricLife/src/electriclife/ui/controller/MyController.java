package electriclife.ui.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

import electriclife.model.Bolletta;
import electriclife.model.Tariffa;
import electriclife.persistence.BadFileFormatException;
import electriclife.persistence.BollettaWriter;
import electriclife.persistence.TariffeReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyController implements Controller {
	private HashMap<String, Tariffa> mappaTariffe = new HashMap<String, Tariffa>();

	public void leggiTariffe(TariffeReader tariffeReader) throws IOException, BadFileFormatException {
		Collection<Tariffa> tariffe = tariffeReader.caricaTariffe();
		for (Tariffa tariffa : tariffe) {
			mappaTariffe.put(tariffa.getNome(), tariffa);
		}
	}

	@Override
	public ObservableList<Tariffa> getTariffe() {
		ObservableList<Tariffa> tariffe = FXCollections.observableArrayList(mappaTariffe.values());
		FXCollections.sort(tariffe);
		return tariffe;
	}

	@Override
	public Bolletta creaBolletta(LocalDate date, String nomeTariffa, int consumo) {
		Tariffa tariffa = mappaTariffe.get(nomeTariffa);
		Bolletta bolletta = tariffa.creaBolletta(date, consumo);
		return bolletta;
	}

	@Override
	public void stampaBolletta(Bolletta b, BollettaWriter bollettaWriter) {
		bollettaWriter.stampaBolletta(b);
	}

}
