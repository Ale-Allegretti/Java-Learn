package elezioni.persistenza;

import java.io.IOException;
import java.io.Reader;

import elezioni.model.Risultato;

public interface EleReader {
	
	public Risultato readAll(Reader rdr) throws IOException, BadFileFormatException ;
	
}
