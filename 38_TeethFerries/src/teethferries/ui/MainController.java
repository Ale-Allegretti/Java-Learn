package teethferries.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;

public class MainController implements Controller {

	private List<Tratta> tratte;
	private List<Servizio> servizi;
	
	
	public MainController(List<Tratta> tratte, List<Servizio> servizi) {
		if (tratte == null)
			throw new IllegalArgumentException("tratte");
		if (servizi == null)
			throw new IllegalArgumentException("servizi");
		this.tratte = tratte;
		this.servizi = servizi;
	}

	@Override
	public List<Porto> elencoPorti() {
		List<Porto> porti = new ArrayList<>();
		List<Porto> arrivi = new ArrayList<>();
		List<Porto> partenze = new ArrayList<>();
		tratte.stream().forEach(t ->arrivi.add(t.getPortoArrivo()));	// estratto tutti i porti
		tratte.stream().forEach(t ->arrivi.add(t.getPortoPartenza()));
		porti.addAll(arrivi.stream().distinct().collect(Collectors.toList()));	// consegno solo i non duplicati
		porti.addAll(partenze.stream().distinct().collect(Collectors.toList()));
		porti.add(Porto.ANY);
		return porti;
	}

	@Override
	public List<Tratta> listaTratte() {
		return tratte;
	}

	@Override
	public List<Servizio> listaServizi() {
		return servizi;
	}

}
