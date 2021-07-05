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

import impostezannonia.model.Fascia;

public class MyFasceReader implements FasceReader {

	@Override
	public List<Fascia> leggiFasceReddito(Reader reader) throws BadFileFormatException {
		if (reader == null)
			throw new IllegalArgumentException("reader");

		List<Fascia> listaScaglioni = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(reader);
			NumberFormat formatter = NumberFormat.getNumberInstance(Locale.ITALY);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, "-:\t");
				String min = tokenizer.nextToken().trim();
				String max = tokenizer.nextToken().trim(); 
				String perc = tokenizer.nextToken().trim();
				// verifiche consistenza formato
				double sogliaMin = formatter.parse(min).doubleValue();
				Fascia scaglione = 
						max.equals("top") ? new Fascia(sogliaMin, perc) 
										    : new Fascia(sogliaMin, formatter.parse(max).doubleValue(), perc);
				listaScaglioni.add(scaglione);
			}
		} catch (IOException | ParseException | NoSuchElementException e) {
			throw new BadFileFormatException(e + "Errore lettura scaglioni");
		}
		return listaScaglioni;
	}

}
