package vendoerivendo.controller;

import java.io.IOException;
import java.util.List;

import vendoerivendo.model.Annuncio;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.MyAnnuncio;
import vendoerivendo.model.Prodotto;
import vendoerivendo.persistence.BadFileFormatException;
import vendoerivendo.persistence.ProdottiReader;

public class MyController implements Controller {
	
	private List<Prodotto> lista;

	public MyController(ProdottiReader prodottiReader) throws IOException, BadFileFormatException {
		lista = prodottiReader.leggiProdotti();
	}

	public Annuncio generaAnnuncio(Prodotto prodotto, Estetica e, double sconto){
		return new MyAnnuncio(prodotto, e, sconto);
	}

	public List<Prodotto> getListaProdotti() {
		return lista;
	}

	

}
