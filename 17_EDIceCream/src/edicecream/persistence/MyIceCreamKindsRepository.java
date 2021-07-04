package edicecream.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

import edicecream.model.IceCreamKind;

public class MyIceCreamKindsRepository implements IceCreamKindsRepository {
	private HashMap<String,IceCreamKind> data;
//	private static final Pattern FLOAT_NUMBER = Pattern.compile("[0-9]+(,[0-9]+)?");
	private static final NumberFormat NUMBER_FORMAT = NumberFormat.getNumberInstance(Locale.ITALY);

	public MyIceCreamKindsRepository(Reader baseReader) throws IOException, BadFileFormatException {
		if (baseReader == null) {
			throw new IllegalArgumentException("Reader nullo");
		}

		data = new HashMap<>();
		BufferedReader reader = new BufferedReader(baseReader);
		String line = null;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}
			String splitted[] = line.split("-");
			if (splitted.length != 4) {
				throw new BadFileFormatException("Formato file errato");
			}
			String name;
			Number price;
			int numFlavors;
			int weightPerFlavor;

			name = splitted[0].trim();
			if (name.length() == 0) {
				throw new BadFileFormatException("Formato file errato");
			}
			try {				
				String value = splitted[1].trim();
				// analisi (puntuale) non richiesta della stringa
//				ParsePosition pos = new ParsePosition(0);
//				price = NUMBER_FORMAT.parse(value, pos);
//				if (pos.getIndex() != value.length() || pos.getErrorIndex() != -1 || price == null) {
//				    throw new BadFileFormatException("Formato prezzo errato");
//				}
				
				price = NUMBER_FORMAT.parse(value);
				
				numFlavors = Integer.parseInt(splitted[2].trim());
				weightPerFlavor = Integer.parseInt(splitted[3].trim());
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Formato file errato", e);
			} catch (ParseException e) {	// <- non necessario se usato NumberFormat.parse con due parametri
				throw new BadFileFormatException("Formato file errato", e);
			}
			data.put(name, new IceCreamKind(name, price.floatValue(), numFlavors, weightPerFlavor));
		}
	}

	@Override
	public IceCreamKind getIceCreamKind(String kind) {
		return data.get(kind);	
	}

	@Override
	public Set<String> getKindNames() {
		return data.keySet();
	}
}
