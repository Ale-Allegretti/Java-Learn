package edicecream.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import edicecream.model.IceCreamKind;

public class MyIceCreamKindsRepository implements IceCreamKindsRepository {

	private Map<String, IceCreamKind> tipoGelati;
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.ITALY);
	
	private String readLineSkippingEmpty(BufferedReader innerReader) throws IOException {
		String riga;
		do {
			riga = innerReader.readLine();
		} while (riga != null && riga.trim().isEmpty());
		return riga;
	}
	
	public MyIceCreamKindsRepository(Reader baseReader) throws IOException, BadFileFormatException {
		if (baseReader == null)
			throw new IllegalArgumentException("File corrotto");
		tipoGelati = new HashMap<String, IceCreamKind>();
		BufferedReader reader = new BufferedReader(baseReader);
		String riga = null;
		while ((riga = readLineSkippingEmpty(reader)) != null) {
			String[] items = riga.trim().split("-");
			if (items.length < 4)
				throw new BadFileFormatException("dati assenti");
			int weight;
			Number price;
			int maxFlavors;
		
			try {
				price = NUMBER_FORMAT.parse(items[1].trim());
				maxFlavors = Integer.parseInt(items[2].trim());
				weight = Integer.parseInt(items[3].trim());
				
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("quantita' non numerica in riga");
			}
			catch (ParseException e) {
				throw new BadFileFormatException("Formato file errato", e);
			}
			if (items[0].trim().equals("")) {
				throw new BadFileFormatException("descrizione vuota in riga");
			}
			
			tipoGelati.put(items[0].trim(), new IceCreamKind(items[0].trim(), price.floatValue(), maxFlavors, weight));
		}
		
	}
	
	
	
	@Override
	public Set<String> getKindNames() {
		return this.tipoGelati.keySet();
	}

	@Override
	public IceCreamKind getIceCreamKind(String kind) {
		return this.tipoGelati.get(kind);
	}

}
