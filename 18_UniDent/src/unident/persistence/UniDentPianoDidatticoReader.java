package unident.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import unident.model.AttivitaFormativa;
import unident.model.Ssd;
import unident.model.Tipologia;

public class UniDentPianoDidatticoReader implements PianoDidatticoReader {
	
	private String readLineSkippingEmpty(BufferedReader innerReader) throws IOException {
		String riga;
		do {
			riga = innerReader.readLine();
		} while (riga != null && riga.trim().isEmpty());
		return riga;
	}
	

	@Override
	public Set<AttivitaFormativa> readAll(Reader rdr) throws IOException,BadFileFormatException {
		if (rdr == null)
			throw new BadFileFormatException("File corrotto");
		Set<AttivitaFormativa> res = new HashSet<>();
		BufferedReader reader = new BufferedReader(rdr);
		String riga = null;
		while ((riga = readLineSkippingEmpty(reader)) != null) {
			String[] items = riga.trim().split("\\t+");
			if (items.length<5 || items.length>6) 
				throw new BadFileFormatException("Riga non contiene il giusto numero di elementi: ");
			String nome = items[1].trim();
			Tipologia t = null;
			try {
				t = Tipologia.valueOf(items[3]);
			} catch(IllegalArgumentException e) {
				throw new BadFileFormatException("Tipologia non valida:" + t);
			}
			Ssd ssd = null; 
			int cfu = 0;
			if (t.ordinal() >= Tipologia.E.ordinal()) {
				ssd = Ssd.SENZASETTORE; 
				cfu = Integer.parseInt(items[4]);
			} 
			else {
				try {
					ssd = Ssd.of(items[4]); 
					cfu = Integer.parseInt(items[5]);
				}
				catch(IndexOutOfBoundsException e) {
					throw new BadFileFormatException("Ssd non valido:" + ssd);
				}
				catch(NumberFormatException e) {
					throw new BadFileFormatException("Cfu non validi:" + cfu);
				}
			}
			AttivitaFormativa afDaInserire = new AttivitaFormativa(nome, t, ssd, cfu);
			boolean buonEsito = res.add(afDaInserire);
			if (!buonEsito) 
				throw new BadFileFormatException("AF duplicata:" + afDaInserire);
		}	
		return res;
	}

}
