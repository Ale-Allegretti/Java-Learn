package ghigliottina.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ghigliottina.model.Esatta;
import ghigliottina.model.Ghigliottina;
import ghigliottina.model.Terna;

public class MyGhigliottineReader implements GhigliottineReader {

	private List<Ghigliottina> ghigliottine;
	
	private Ghigliottina parseOne(BufferedReader reader) throws IOException, BadFileFormatException {
		String esatta = null;
		List<Terna> terne = new ArrayList<>();
		String line=reader.readLine();
		
		if (line == null) 
			return null;
		line = line.trim();
		if (line.startsWith("------")) 
			throw new BadFileFormatException("Ghigliottina vuota, manca tutto!");
		
		while(line != null && !line.startsWith("Risposta esatta")) {

			String[] items = line.split("/|=");// word1,word2,FIRST|SECOND
			if(items.length!=3) 
				throw new BadFileFormatException("Ghigliottina mal formattata, terna errata alla riga " + line);
			try {
				Terna terna = new Terna(items[0].trim(),items[1].trim(), Esatta.valueOf(items[2].trim()));
				terne.add(terna);
				line = reader.readLine().trim();
			}
			catch(IllegalArgumentException e) {
				throw new BadFileFormatException("Ghigliottina mal formattata, manca FIRST|SECOND");
			}
		}
		// ora dovrebbe esserci la risposta esatta
		if (!line.startsWith("Risposta esatta")) 
			throw new BadFileFormatException("Ghigliottina mal formattata, manca la risposta esatta!");
		else {
			String[] items = line.split("=");
			if(items.length!=2) 
				throw new BadFileFormatException("Ghigliottina mal formattata alla riga " + line);
			esatta=items[1].trim();
		}
		line = reader.readLine();
		// ora infine dovrebbe esserci la riga con le lineette
		if (line==null || !line.trim().startsWith("------")) 
			throw new BadFileFormatException("Ghigliottina mal formattata, manca la riga di separazione");
		return new Ghigliottina(terne,esatta);
	}

	public List<Ghigliottina> readAll(BufferedReader reader) throws IOException, BadFileFormatException {
		Ghigliottina gh;
		List<Ghigliottina> ghigliottine = new ArrayList<>();;
		while((gh = parseOne(reader)) != null) {
			ghigliottine.add(gh);
		}
		return ghigliottine;
	}

	public List<Ghigliottina> getGhigliottine() {
		return ghigliottine;
	}
	
	
	public static void main(String[] args) throws IOException {
		
	}

}
