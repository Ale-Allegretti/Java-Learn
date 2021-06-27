package edicecream.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class MyIceCreamRepository implements IceCreamRepository {

	private Map<String, Integer> scorte;
	
	private String readLineSkippingEmpty(BufferedReader innerReader) throws IOException {
		String riga;
		do {
			riga = innerReader.readLine();
		} while (riga != null && riga.trim().isEmpty());
		return riga;
	}
	
	public MyIceCreamRepository (Reader baseReader) throws IOException, BadFileFormatException {
		if (baseReader == null)
			throw new IllegalArgumentException("File corrotto");
		scorte = new HashMap<String, Integer>();
		BufferedReader reader = new BufferedReader(baseReader);
		String riga = null;
		while ((riga = readLineSkippingEmpty(reader)) != null) {
			String[] items = riga.trim().split("-");
			if (items.length < 2)
				throw new BadFileFormatException("dati assenti");
			int index;
			try {
				index = Integer.parseInt(items[1].trim());
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("quantita' non numerica in riga");
			}
			if (items[0].trim().equals("")) {
				throw new BadFileFormatException("descrizione vuota in riga");
			}
			
			scorte.put(items[0].trim(), index);
		}
	}
	
	@Override
	public Set<String> getFlavors() {
		return this.scorte.keySet();
	}

	@Override
	public boolean removeQuantity(String flavor, int qty) {;
		Integer current = scorte.get(flavor);
		if (current == null || current.intValue() < qty) {
			return false;
		}
		scorte.put(flavor, current - qty);
		return true;
	}

	@Override
	public Integer getAvailableQuantity(String flavor) {
		return this.scorte.get(flavor);
	}

}
