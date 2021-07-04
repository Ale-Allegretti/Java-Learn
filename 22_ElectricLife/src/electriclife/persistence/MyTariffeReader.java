package electriclife.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import electriclife.model.Tariffa;
import electriclife.model.TariffaAConsumo;
import electriclife.model.TariffaFlat;

public class MyTariffeReader implements TariffeReader {
	private Reader innerReader;
	private boolean aConsumoGiàTrovata;

	public MyTariffeReader(Reader reader) {
		this.innerReader = reader;
		aConsumoGiàTrovata = false;
	}

	@Override
	public Collection<Tariffa> caricaTariffe() throws IOException, BadFileFormatException {
		List<Tariffa> elencoTariffe = new ArrayList<Tariffa>();
		BufferedReader fin = null;
		String line;
		try {
			fin = new BufferedReader(innerReader);

			while ((line = fin.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, ";\t");
				String tipo = tokenizer.nextToken().trim();
				Tariffa t = null;
				switch (tipo.toUpperCase()) {
				case "FLAT": {
					t = readFlat(tokenizer);
					break;
				}
				case "A CONSUMO": {
					t = readConsumo(tokenizer);
					break;
				}
				default:
					throw new BadFileFormatException("Tariffa non riconosciuta");
				}
				elencoTariffe.add(t);
			}
			fin.close();
		} catch (IOException | NoSuchElementException | NumberFormatException e) {
			throw new BadFileFormatException(e);
		}
		return elencoTariffe;
	}

	private Tariffa readConsumo(StringTokenizer tokenizer) throws BadFileFormatException {
		if (aConsumoGiàTrovata)
			throw new BadFileFormatException("Tariffa a consumo duplicata");
		else
			aConsumoGiàTrovata = true;
		// A CONSUMO ; € 0,14
		NumberFormat euroFormatter = NumberFormat.getNumberInstance(Locale.ITALY);
		@SuppressWarnings("unused")
		String toThrowAway = tokenizer.nextToken("€").trim(); // "; €"
		String numPrezzo = tokenizer.nextToken("\r\n").replace("€", "").trim();
		double prezzo;

		ParsePosition position = new ParsePosition(0);
		prezzo = euroFormatter.parse(numPrezzo, position).doubleValue();
		System.out.println(prezzo);
		if (position.getIndex() != numPrezzo.length()) {
			throw new BadFileFormatException("prezzo " + numPrezzo + " @ " + position.getIndex());
		}
		return new TariffaAConsumo("A CONSUMO", prezzo);
	}

	private Tariffa readFlat(StringTokenizer tokenizer) throws BadFileFormatException {
		// FLAT; CASA MAXI; SOGLIA 450; € 50,00; KWh EXTRA € 0,21
		NumberFormat euroFormatter = NumberFormat.getNumberInstance(Locale.ITALY);
		String nome = tokenizer.nextToken().trim();

		String wordSoglia = tokenizer.nextToken(" \t;").trim();
		if (!wordSoglia.equalsIgnoreCase("SOGLIA"))
			throw new BadFileFormatException("Expected SOGLIA");
		String numSoglia = tokenizer.nextToken(";").trim();
		int soglia = Integer.parseInt(numSoglia);

		@SuppressWarnings("unused")
		String toThrowAway = tokenizer.nextToken("€").trim(); // "; �"
		String numPrezzo = tokenizer.nextToken(";").replace("€", "").trim();
		double prezzo;
		System.out.println(numPrezzo);
		ParsePosition position = new ParsePosition(0);
		
		prezzo = euroFormatter.parse(numPrezzo, position).doubleValue();
		if (position.getIndex() != numPrezzo.length()) {
			throw new BadFileFormatException("prezzo " + numPrezzo + " @ " + position.getIndex());
		}

		toThrowAway = tokenizer.nextToken("K").trim(); // ";"
		String wordKWhExtra = tokenizer.nextToken("€").trim();
		if (!wordKWhExtra.equalsIgnoreCase("KWh EXTRA"))
			throw new BadFileFormatException("Expected KWh EXTRA");

		String numExtra = tokenizer.nextToken("\n\r").replace("€", "").trim();

		double extra;
		position = new ParsePosition(0);
		extra = euroFormatter.parse(numExtra, position).doubleValue();
		if (position.getIndex() != numExtra.length()) {
			throw new BadFileFormatException("prezzo " + numExtra + " @ " + position.getIndex());
		}

		return new TariffaFlat(nome, prezzo, soglia, extra);
	}

}
