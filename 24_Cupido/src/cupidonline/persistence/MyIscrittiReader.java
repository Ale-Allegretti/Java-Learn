package cupidonline.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import cupidonline.model.Colore;
import cupidonline.model.Persona;
import cupidonline.model.SegnoZodiacale;
import cupidonline.model.Sesso;

public class MyIscrittiReader implements IscrittiReader {

	@Override
	public Map<String, Persona> caricaIscritti(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new BadFileFormatException("File corrotto");
		Map<String, Persona> resMap = new HashMap<String, Persona>();
		String line;
		
		try (BufferedReader fin = new BufferedReader(reader)) {
			while((line = fin.readLine()) != null) {
				String[] items = line.split(",");
				if (items.length < 10)
					throw new BadFileFormatException("Numero informazioni errato");
				String nome = items[0].strip();
				Sesso sesso = Sesso.valueOfChar(items[1].strip());
				LocalDate nascita = LocalDate.parse(items[2].strip(), DateTimeFormatter.ISO_DATE);
				SegnoZodiacale segnoZodiacale = null;
				for (SegnoZodiacale segno : SegnoZodiacale.values())
					if (segno.contains(nascita))
						segnoZodiacale = segno;
				String[] capelliString = items[3].strip().split("\\s+");
				if (!capelliString[0].equalsIgnoreCase("capelli"))
					throw new BadFileFormatException("File corrotto");
				Colore capelli = Colore.valueOf(capelliString[1].trim().toUpperCase());
				String occhiString[] = items[4].strip().split("\\s+");
				if (!occhiString[0].equalsIgnoreCase("occhi"))
					throw new BadFileFormatException("File corrotto");
				Colore occhi = Colore.valueOf(occhiString[1].trim().toUpperCase());
				NumberFormat format = NumberFormat.getInstance(Locale.ITALY);
				format.setMaximumFractionDigits(2);
				format.setMinimumFractionDigits(2);
				@SuppressWarnings("unused")
				float altezza = Float.parseFloat(items[5].strip());  
				// uso questo perchè format.parse non fa partire realmente la exception
				float altezzaCm = format.parse(items[5].strip()).floatValue() / 100;
				int peso = Integer.parseInt(items[6].strip());
				String citta = items[7].trim();
				String provincia = items[8].trim();
				String regione = items[9].trim();
				
				
				resMap.put(nome, new Persona(nome, sesso, nascita, segnoZodiacale, capelli, occhi, altezzaCm, peso, citta, provincia, regione));
			}
			System.out.println(resMap.size());
		}
		catch (NumberFormatException | ParseException | DateTimeParseException e) {
			throw new BadFileFormatException("File corrotto");
		}
		catch (IOException e) {
			throw new BadFileFormatException(e);
		} 
		
		return resMap;
	}

}
