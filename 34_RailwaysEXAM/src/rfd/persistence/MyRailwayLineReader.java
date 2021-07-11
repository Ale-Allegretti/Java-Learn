package rfd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import rfd.model.MyPointOfInterest;
import rfd.model.PointOfInterest;
import rfd.model.RailwayLine;

public class MyRailwayLineReader implements RailwayLineReader {

	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException {
		if (rdr == null)
			throw new IllegalArgumentException("Reader nullo");
		
		BufferedReader reader = new BufferedReader(rdr);
		Map<String,PointOfInterest> map = new TreeMap<>();
		SortedSet<String> hubs = new TreeSet<>();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");
			if (tokenizer.countTokens() != 2)
				throw new IllegalArgumentException("Riga mal formattata" + line);
			String progressivaKm = tokenizer.nextToken().trim();
			String[] progressivaSplit = progressivaKm.split("\\+");
				
			if (progressivaSplit[0].length() < 1 || progressivaSplit[0].length() > 3 
					|| Double.valueOf(progressivaSplit[0]).isNaN())
				throw new IllegalArgumentException("Formato chilomentri errato");
			if (progressivaSplit[1].length() != 3 || Double.valueOf(progressivaSplit[1]).isNaN())
				throw new IllegalArgumentException("Formato metri errato");
			
			
			String nomeStazione = tokenizer.nextToken();
			if (nomeStazione.isEmpty() || nomeStazione.replace("+", "").isBlank())
				throw new IllegalArgumentException("Nome stazione mancante");
			
			for (int i = 0; i < 10; i++)
				if (nomeStazione.startsWith(String.valueOf(i)))
					throw new IllegalArgumentException("Formato nome stazione errato");
			
			if (nomeStazione.contains("+")) {
				if(!nomeStazione.endsWith("+"))
					throw new IllegalArgumentException("Formato nome stazione HUB errato");
				String[] nomeEstratto = nomeStazione.split("\\+");
				if (nomeEstratto[0].length() != nomeEstratto[0].trim().length())
					throw new IllegalArgumentException("Formato nome stazione HUB errato");
				if (nomeEstratto[0].trim().isEmpty() || nomeEstratto[0].trim().isBlank())
					throw new IllegalArgumentException("Nome stazione mancante");
				
				nomeStazione = nomeEstratto[0];
				hubs.add(nomeStazione);
			}
			
			map.put(nomeStazione, new MyPointOfInterest(nomeStazione, progressivaKm));
		}
		
		return new RailwayLine(map, hubs);
	}

}
