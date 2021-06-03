package favoliere.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import favoliere.model.Personaggio;
import favoliere.model.Tipologia;

public class PersonaggiLoader implements SectionLoader<Personaggio> {

	private List<Personaggio> personaggi;
	
	@Override
	public List<Personaggio> getItems() {
		return personaggi;
	}

	@Override
	public void loadAllItems(Reader baseReader) throws IOException, BadFileFormatException {
		personaggi = new ArrayList<>();
		BufferedReader reader = new BufferedReader(baseReader);
		String riga = null;
		while ((riga = reader.readLine()) != null) {
			if (riga.trim().isEmpty()) 
				throw new BadFileFormatException("riga vuota in personaggi");
			StringTokenizer tok1 = new StringTokenizer(riga, ":");
			String tipologia = tok1.nextToken().trim();
			
			if (!tipologia.equals("POSITIVO") && !tipologia.equals("NEGATIVO"))
				throw new BadFileFormatException("mancanza tipologia in personaggio");
			Tipologia tipo = Tipologia.valueOf(tipologia);
			
			String nome = tok1.nextToken().trim();
			if (nome.isBlank())
				throw new BadFileFormatException("mancanza nome in personaggio");
			String descrizione = tok1.nextToken().trim();
			if (descrizione.isBlank())
				throw new BadFileFormatException("mancanza nome in personaggio");
			
			personaggi.add(new Personaggio(nome, tipo, descrizione));
			
		}
		
		
		
	}

}
