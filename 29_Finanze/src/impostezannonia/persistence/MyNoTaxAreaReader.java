package impostezannonia.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import impostezannonia.model.TipologiaContribuente;

public class MyNoTaxAreaReader implements NoTaxAreaReader {

	@Override
	public TipologiaContribuente[] leggiNoTaxArea(Reader reader) throws BadFileFormatException {
		if (reader == null)
			throw new IllegalArgumentException("reader");
		
		List<TipologiaContribuente> res = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(reader);
			NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ITALY);
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, "/");
				String descrizione = tokenizer.nextToken().trim();
				String noTaxArea = tokenizer.nextToken().trim();
				
				double contributo = formatter.parse(noTaxArea).doubleValue();
				res.add(new TipologiaContribuente(descrizione, contributo));
			}
			
			
		} catch (IOException | ParseException | NoSuchElementException e) {
			throw new BadFileFormatException(e + "Errore lettura");
		}
	
		return res.toArray(new TipologiaContribuente[0]);
	}

}
