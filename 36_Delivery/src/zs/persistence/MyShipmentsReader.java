package zs.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import zs.model.Recipient;
import zs.model.Shipment;

public class MyShipmentsReader implements ShipmentsReader {
	
	@Override
	public List<Shipment> read(BufferedReader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new BadFileFormatException("Reader nullo");
		List<Shipment> result = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer1 = new StringTokenizer(line, " ");
			if (tokenizer1.countTokens() < 3)
				throw new BadFileFormatException("Formato riga errato");
			String tracking = tokenizer1.nextToken().trim();
			String pesoString = tokenizer1.nextToken().trim();
			float peso;
			try {
				peso = Float.parseFloat(pesoString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Formato peso errato");
			}
			StringTokenizer tokenizer2 = new StringTokenizer(tokenizer1.nextToken("\n"), "@");
			if (tokenizer2.countTokens() != 4)
				throw new BadFileFormatException("Formato riga destinatario errato");
			String nomeCognome = tokenizer2.nextToken().trim();
			System.out.println(nomeCognome);
			String indirizzo = tokenizer2.nextToken().trim();
			String capString = tokenizer2.nextToken().trim();
			String citta = tokenizer2.nextToken().trim();
			if (nomeCognome.isEmpty() || indirizzo.isEmpty() || capString.isEmpty() || citta.isEmpty())
				throw new BadFileFormatException("Formato destinatario errato");
			int cap;
			try {
				cap = Integer.parseInt(capString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Formato CAP errato");
			}
			result.add(new Shipment(tracking, new Recipient(nomeCognome, indirizzo, cap, citta), peso));
		}
		return result;
	}

}
