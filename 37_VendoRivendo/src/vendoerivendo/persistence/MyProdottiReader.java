package vendoerivendo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import vendoerivendo.model.CategoriaMerceologica;
import vendoerivendo.model.Prodotto;

public class MyProdottiReader implements ProdottiReader {

	private List<Prodotto> prodotti;
	private BufferedReader bReader;
	
	public MyProdottiReader(Reader reader) throws IOException, BadFileFormatException{
		if (reader == null)
			throw new BadFileFormatException("Reader nullo");
		this.bReader = new BufferedReader(reader);
	}
	
	@Override
	public List<Prodotto> leggiProdotti() throws IOException, BadFileFormatException {
		String line;
		this.prodotti = new ArrayList<>();
		while ((line = bReader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");
			if (tokenizer.countTokens() != 4)
				throw new BadFileFormatException("Numero di token diverso dalle attese (= 4)");
			String nome = tokenizer.nextToken().trim();
			String prezzoString = tokenizer.nextToken().trim();
			double prezzo;
			try {
				prezzo = Double.parseDouble(prezzoString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Formato prezzo errato");
			}
			String descrizione = tokenizer.nextToken().trim();
			String categoriaString = tokenizer.nextToken().trim();
			CategoriaMerceologica categoria = CategoriaMerceologica.valueOf(categoriaString);
			if (categoria == null)
				throw new BadFileFormatException("Formato categoria errato");
			this.prodotti.add(new Prodotto(nome, descrizione, prezzo, categoria));
		}
		return this.prodotti;
	}

}
