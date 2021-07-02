package elezioni.persistenza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.StringTokenizer;

import elezioni.model.Coppia;
import elezioni.model.Risultato;

public class MyEleReader implements EleReader {
	
	public Risultato readAll(Reader rdr) throws IOException, BadFileFormatException {
		Risultato risultato = new Risultato();
		
		BufferedReader reader = new BufferedReader(rdr);
		String line;
		while ((line=reader.readLine())!=null) {
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");
			String citta = tokenizer.nextToken().trim();
			String[] items = tokenizer.nextToken("\n\r").trim().split(",");
			//System.out.println("citta=" + citta + ", voti=" + Arrays.asList(items));
			//System.out.println("citta=" + citta + ", voti=" + Arrays.stream(items).map(Object::toString).reduce((x,y) -> x+y));
			for (String item : items) {
				String[] subitems = item.trim().split("\\t+|\\s+");
				//System.out.println(Arrays.asList(subitems));
				try {
					risultato.add(citta, new Coppia(subitems[0], subitems[1].trim()));
				} catch (ParseException e) {
					throw new BadFileFormatException("Not a long: " + subitems[1].trim());
				}
			}
		}
		return risultato;
	}
	
}
