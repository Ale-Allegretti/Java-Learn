package favoliere.model;

import java.util.*;
import java.util.stream.Collectors;

public class MySintetizzatore implements Sintetizzatore {

	private List<Azione> azioni;
	private List<Conclusione> conclusioni;
	private List<Personaggio> personaggi;
	private List<Scenario> scenari;
	
	
	public MySintetizzatore(List<Personaggio> personaggi, List<Scenario> scenari, List<Azione> azioni,
			List<Conclusione> conclusioni) {
		this.personaggi = personaggi;
		this.scenari = scenari;
		this.azioni = azioni;
		this.conclusioni = conclusioni;
	}
	
	
	@Override
	public List<Personaggio> getPersonaggi() {
		return personaggi;
	}

	@Override
	public List<Scenario> getScenari() {
		return scenari;
	}

	@Override
	public List<Azione> getAzioni() {
		return azioni;
	}

	@Override
	public List<Conclusione> getConclusioni() {
		return conclusioni;
	}
	
	private Set<Personaggio> sorteggia(List<Personaggio> lista, int n, Tipologia tipo) throws NoSuchTaleException {
		Random r = new Random();
		
		/* List<Personaggio> perTipo = new ArrayList<Personaggio>();
		Set<Personaggio> res = new HashSet<Personaggio>();
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getTipologia().compareTo(tipo) == 0)
				perTipo.add(lista.get(i)); 
		int indice = 0;
		if (!perTipo.isEmpty()) {
			do {
				res.add(perTipo.get(r.nextInt(perTipo.size())));
				indice++;
			} while (indice <= n);
		} */
		
		Set<Personaggio> personaggiScelti = lista.stream()
												 .filter(e -> e.getTipologia() == tipo)
												 .collect(Collectors.toSet());
		
		if (personaggiScelti.size() < n) 
			throw new NoSuchTaleException("Non ci sono sufficienti personaggi del tipo richiesto");
		List<Personaggio> listaPersonaggiScelti = personaggiScelti.stream().collect(Collectors.toList());
		
		return 
				r.ints(0, listaPersonaggiScelti.size()) 
				 .distinct()
				 .limit(n)
				 .mapToObj(listaPersonaggiScelti::get) //qui mi serve la lista per estrarre l'indice del random selezionato, non posso usare il set
				 .collect(Collectors.toSet());
	}
	
	private <T> T sorteggia(List<T> lista) {
		Random r = new Random();
		return lista.get(r.nextInt(lista.size()));
	}
	
	private <T extends ConIndice> Optional<T> sorteggia(List<T> lista, int upperBound) {
		Random r = new Random();
		T result = null;
		int k;

		/*List<T> perTipo = new ArrayList<T>();
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getIndice() < upperBound)
				perTipo.add(lista.get(i)); */
		
		Set<T> indiceMinoreUpperBound = lista.stream()
											 .filter(e -> e.getIndice() <= upperBound)
											 .collect(Collectors.toSet());
		
		if (!indiceMinoreUpperBound.isEmpty()) {
			do {
				k = r.nextInt(lista.size());
				if (lista.get(k).getIndice() <= upperBound) 
					result = lista.get(k);
			} while(lista.get(k).getIndice() > upperBound);
		}
		
		return Optional.ofNullable(result);
		
	}

	@Override
	public Favola generaFavola(FasciaEta eta, Impressionabilita livelloImpressionabilita) throws NoSuchTaleException {
		
		Set<Personaggio> personaggiCattivi = this.sorteggia(this.personaggi, 1, Tipologia.NEGATIVO);
		Set<Personaggio> personaggiBuoni = this.sorteggia(this.personaggi, 2, Tipologia.POSITIVO);
		
		Esordio esordio = new Esordio(personaggiBuoni, personaggiCattivi);
		
		Optional<Scenario> scenario = this.sorteggia(this.scenari, eta.getGradoComplessita());
		if (scenario.isEmpty()) 
			throw new NoSuchTaleException("Non esistono scenari con il grado di complessità richiesto");
		
		Optional<Azione> azione = this.sorteggia(this.azioni, livelloImpressionabilita.getGradoDurezza());
		if (azione.isEmpty())
			throw new NoSuchTaleException("Non esistono azioni con il grado di durezza richiesto");
		
		Conclusione conclusione = sorteggia(this.conclusioni);

		return new Favola(esordio, scenario.get(), azione.get(), conclusione);
	}

}
