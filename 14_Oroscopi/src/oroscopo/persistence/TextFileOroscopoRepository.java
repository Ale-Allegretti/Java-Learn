package oroscopo.persistence;

import java.io.*;
import java.util.*;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class TextFileOroscopoRepository implements OroscopoRepository {
	
	private Map<String, List<Previsione>> data;
	
	public TextFileOroscopoRepository(Reader baseReader) throws BadFileFormatException, IOException {
		if (baseReader == null)
			throw new IllegalArgumentException("Reader non valido");
		
		BufferedReader bufferedReader = new BufferedReader(baseReader);
		data = new HashMap<String, List<Previsione>>();
		
		String line = null;
		while ((line = readLineSkippingEmpty(bufferedReader)) != null) {
			if (line.contains(" ") || line.contains("\t"))
				throw new BadFileFormatException("linea sezione errata");
				
			data.put(line.trim().toLowerCase(), caricaPrevisione(bufferedReader));
		}
		
	}
	
	private List<Previsione> caricaPrevisione(BufferedReader reader) throws BadFileFormatException, IOException{
		List<Previsione> res = new ArrayList<Previsione>();
		String line;
		try {
			while (!(line = readLineSkippingEmpty(reader).trim()).equalsIgnoreCase("FINE")) {
				StringTokenizer tok1 = new StringTokenizer(line, "\t\r\n");
			
				String frase = tok1.nextToken().trim();
				int gradoFortuna = Integer.parseInt(tok1.nextToken().trim());
				Previsione previsione = null;
			
				if (tok1.hasMoreTokens()) {
					String allowedSignsToken = tok1.nextToken();
					try {
						String[] signs = allowedSignsToken.split(",");
						Set<SegnoZodiacale> allowedSigns = new HashSet<>();
						for (String sign : signs) 
							allowedSigns.add(SegnoZodiacale.valueOf(sign.trim().toUpperCase()));
						previsione = new Previsione(frase, gradoFortuna, allowedSigns);
					}
					catch (Exception e){
						throw new BadFileFormatException("lista segni errata");
					}
				}
				else 
					previsione = new Previsione(frase, gradoFortuna);	
				
				res.add(previsione);
			}
		}
		catch (Exception e){
			throw new BadFileFormatException(e);
		}
		if (res.size() == 0)
			throw new BadFileFormatException("Nessuna previsione");
		
		return res;
	}
	
	private String readLineSkippingEmpty(BufferedReader innerReader) throws IOException {
		String riga;
		do {
			riga = innerReader.readLine();
		} while (riga != null && riga.trim().isEmpty());
		return riga;
	}

	@Override
	public Set<String> getSettori() {
		Set<String> settori = new HashSet<String>();
		for (String s : this.data.keySet())
			if (!this.data.get(s).isEmpty())
				settori.add(s);
		
		return settori;
	}

	@Override
	public List<Previsione> getPrevisioni(String settore) {
		for (String s : this.data.keySet())
			if (s.equalsIgnoreCase(settore))
				return this.data.get(s);
		
		return null;
	}

	

}
