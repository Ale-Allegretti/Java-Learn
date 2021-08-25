package teethferries.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import teethferries.model.Porto;
import teethferries.model.Tratta;

public class MyTratteReader implements TratteReader {

	@Override
	public List<Tratta> leggiTratte(Reader tratteReader) throws IOException, MalformedFileException {
		if (tratteReader == null)
			throw new MalformedFileException("Reader nullo");
		BufferedReader reader = new BufferedReader(tratteReader);
		List<Tratta> tratte = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, "\t");
			if (tokenizer.countTokens() != 3)
				throw new MalformedFileException("Numero di token diverso dalle attese (= 4)");
			String id = tokenizer.nextToken().trim();
			String partenza =  tokenizer.nextToken().trim();
			String arrivo =  tokenizer.nextToken().trim();
			
			tratte.add(new Tratta(id, new Porto(partenza), new Porto(arrivo)));
		}
		
		return tratte;
	}

}
