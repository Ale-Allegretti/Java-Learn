package electriclife.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import electriclife.model.Tariffa;
import electriclife.model.TariffaAConsumo;
import electriclife.model.TariffaFlat;

public class MyTariffeReader implements TariffeReader {
	private Reader innerReader;
	private boolean aConsumoGiaTrovata;

	public MyTariffeReader(Reader reader) {
		this.innerReader = reader;
		aConsumoGiaTrovata = false;
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
		if (aConsumoGiaTrovata)
			throw new BadFileFormatException("Tariffa a consumo duplicata");
		else
			aConsumoGiaTrovata = true;
		// A CONSUMO ; € 0,14
		//NumberFormat euroFormatter = NumberFormat.getNumberInstance(Locale.ITALY);
		@SuppressWarnings("unused")
		String toThrowAway = tokenizer.nextToken("€").trim(); // "; €"
		DecimalFormat currencyF = new DecimalFormat("¤ #,##0.##");
		String numPrezzo = tokenizer.nextToken("\r\n").trim();
		ParsePosition position = new ParsePosition(0);
		double prezzo = currencyF.parse(numPrezzo, position).doubleValue();
		System.out.println(prezzo);
		if (position.getIndex() != numPrezzo.length()) {
			throw new BadFileFormatException("prezzo " + numPrezzo + " @ " + position.getIndex());
		}
		return new TariffaAConsumo("A CONSUMO", prezzo);
	}

	private Tariffa readFlat(StringTokenizer tokenizer) throws BadFileFormatException {
		// FLAT; CASA MAXI; SOGLIA 450; € 50,00; KWh EXTRA € 0,21
		//NumberFormat euroFormatter = NumberFormat.getNumberInstance(Locale.ITALY);
		
		DecimalFormat currencyF = new DecimalFormat("¤ #,##0.##");
		currencyF.setCurrency(Currency.getInstance(Locale.ITALY));
		String nome = tokenizer.nextToken().trim();
		String wordSoglia = tokenizer.nextToken(" \t;").trim();
		if (!wordSoglia.equalsIgnoreCase("SOGLIA"))
			throw new BadFileFormatException("Expected SOGLIA");
		String numSoglia = tokenizer.nextToken(";").trim();
		int soglia = Integer.parseInt(numSoglia);

		@SuppressWarnings("unused")
		String toThrowAway = tokenizer.nextToken("€").trim(); // "; �"
		
		String numPrezzo = tokenizer.nextToken(";").trim();
		ParsePosition position = new ParsePosition(0);
		double prezzo = currencyF.parse(numPrezzo, position).doubleValue();
		if (position.getIndex() != numPrezzo.length()) {
			throw new BadFileFormatException("prezzo " + numPrezzo + " @ " + position.getIndex());
		}

		toThrowAway = tokenizer.nextToken("K").trim(); // ";"
		String wordKWhExtra = tokenizer.nextToken("€").trim();
		if (!wordKWhExtra.equalsIgnoreCase("KWh EXTRA"))
			throw new BadFileFormatException("Expected KWh EXTRA");

		String numExtra = tokenizer.nextToken("\n\r").trim();

		position = new ParsePosition(0);
		double extra = currencyF.parse(numExtra, position).doubleValue();
		if (position.getIndex() != numExtra.length()) {
			throw new BadFileFormatException("prezzo " + numExtra + " @ " + position.getIndex());
		}

		return new TariffaFlat(nome, prezzo, soglia, extra);
	}

}
