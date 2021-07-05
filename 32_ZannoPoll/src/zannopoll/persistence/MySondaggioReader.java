package zannopoll.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import zannopoll.model.Intervista;
import zannopoll.model.Sesso;

public class MySondaggioReader implements SondaggioReader {

	
	
	@Override
	public List<Intervista> leggiRisposte(Reader r) throws IOException, BadFileFormatException {
		if (r == null)
			throw new IllegalArgumentException("Reader nullo");
		BufferedReader reader = new BufferedReader(r);
		String line;
		List<Intervista> res = new ArrayList<>();
		
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, ",");
			System.out.println(line);
			if (tokenizer.countTokens() < 3)
				throw new BadFileFormatException("riga errata");
			String nome = tokenizer.nextToken().trim();
			
			if (nome.isBlank())
				throw new BadFileFormatException("nome assente");
			String votiString = tokenizer.nextToken().trim();
			int voti;
			try {
				voti = Integer.parseInt(votiString);
			}
			catch (NumberFormatException e) {
				throw new BadFileFormatException("numero voti errato");
			}
			String nomeSesso = tokenizer.nextToken().strip();
			
			if (nomeSesso.equals("M"))
				res.add(new Intervista(nome, voti, Sesso.MASCHIO));
			else if (nomeSesso.equals("F"))
				res.add(new Intervista(nome, voti, Sesso.FEMMINA));
			else 
				throw new BadFileFormatException("sesso non conforme");
		}
		
		return res;
	}

}
