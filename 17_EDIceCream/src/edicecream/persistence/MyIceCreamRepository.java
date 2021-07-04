package edicecream.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Set;

public class MyIceCreamRepository implements IceCreamRepository {

	private HashMap<String, Integer> stock;

	public MyIceCreamRepository(Reader baseReader) throws IOException, BadFileFormatException {
		if (baseReader == null) {
			throw new IllegalArgumentException("Reader nullo");
		}

		stock = new HashMap<>();
		BufferedReader reader = new BufferedReader(baseReader);
		String line = null;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}
			String splitted[] = line.split("-");
			if (splitted.length != 2) {
				throw new BadFileFormatException("Formato file errato");
			}
			String flavor;
			int value;

			flavor = splitted[0].trim();
			if (flavor.length() == 0) {
				throw new BadFileFormatException("Formato file errato");
			}
			try {
				value = Integer.parseInt(splitted[1].trim());
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Formato file errato", e);
			}
			stock.put(flavor, value);
		}
	}

	@Override
	public Set<String> getFlavors() {
		return stock.keySet();
	}

	@Override
	public boolean removeQuantity(String flavor, int qty) {
		Integer current = stock.get(flavor);
		if (current == null || current.intValue() < qty) {
			return false;
		}
		stock.put(flavor, current - qty);
		return true;
	}
	
	@Override
	public Integer getAvailableQuantity(String flavor) {
		return stock.get(flavor);
	}
}
