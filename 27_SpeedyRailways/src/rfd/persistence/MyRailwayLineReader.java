package rfd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import rfd.model.RailwayLine;
import rfd.model.Station;

public class MyRailwayLineReader implements RailwayLineReader {
	
	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException {
		SortedMap<String,Station> map = new TreeMap<>();
		SortedSet<String> hubs = new TreeSet<>();
		
		if (rdr == null)
			throw new IllegalArgumentException("File corrotto");
		String line = null;
		NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ITALY);
		formatter.setMaximumFractionDigits(2);
		int lineLength = 0;
		BufferedReader reader = new BufferedReader(rdr);
		while((line = reader.readLine()) != null) {
			if(line.contains("\r\n")) 
				line.replace("\r\n", "");
			if(lineLength == 0) { 
				lineLength = line.length();
			}
			else if (lineLength != line.length()) {
				throw new IllegalArgumentException("linee con lunghezza diversa - errore");
			}
			
			String[] items = line.split("");
			double progKm; int velocita;
			
			try {
			String numbString = "";
			for(int i = 0; i < 8; i++)
				numbString = numbString.concat(items[i]);
			if(!numbString.strip().matches("\\d+(\\,?\\d+)"))
				throw new IllegalArgumentException("progression Km mancante");
			progKm = formatter.parse(numbString.strip()).doubleValue();
			
			String velString = "";
			for(int i = items.length-5; i < items.length; i++)
				velString = velString.concat(items[i]);
			velocita = Integer.parseInt(velString.strip());
			}
			catch (NumberFormatException | ParseException e) {
				throw new IllegalArgumentException("Velocita mancante");
			}
				
			String nome = "";
			for(int i = 8; i < items.length-4; i++)
				nome = nome.concat(items[i]);
			nome = nome.strip();
			String stazione = null;
			boolean stazioneConHub = false;
			if(nome.toUpperCase().contains("HUB")) {
				if(nome.replace("HUB", "").isEmpty() ||nome.replace("HUB", "").isBlank())
					throw new IllegalArgumentException("Nome stazione mancante");
			stazione = nome.substring(0, nome.toUpperCase().indexOf("HUB")).strip();
			stazioneConHub = true;
			}
			else {
				stazione = nome.strip();
				if(stazione.isEmpty() || stazione.isBlank())
					throw new IllegalArgumentException("Nome stazione mancante");
				
			}
			System.out.println(stazione);
			map.put(stazione, new Station(stazione, progKm, velocita));
			if (stazioneConHub)
				hubs.add(stazione);
			}
			
		return new RailwayLine(map, hubs);
	}
	
	public static void main(String[] args) throws ParseException {
		
		String provaString = "   12.74  Anzola dell'Emilia                                         140\r\n";
		if(provaString.contains("\r\n")) 
			provaString.replace("\r\n", "");
		String[] items = provaString.split("");

		NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ITALY);
		formatter.setMaximumFractionDigits(2);
		
		String numbString = "";
		for(int i = 0; i < 8; i++)
			numbString = numbString.concat(items[i]);
		if(numbString.isBlank() || numbString.isEmpty())
			throw new IllegalArgumentException("progression Km mancante");
		
		double progKm = formatter.parse(numbString.strip()).doubleValue();
		
		String velString = "";
		for(int i = items.length-5; i < items.length; i++)
			velString = velString.concat(items[i]);
		int velocita = Integer.parseInt(velString.trim());
		
		
		System.out.println(velocita);
		System.out.println(progKm);
		
	}

}
