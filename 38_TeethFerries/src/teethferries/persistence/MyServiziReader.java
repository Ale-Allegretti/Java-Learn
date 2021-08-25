package teethferries.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import teethferries.model.Servizio;
import teethferries.model.Tratta;

public class MyServiziReader implements ServiziReader {
	
	@Override
	public List<Servizio> leggiServizi(Reader reader, List<Tratta> tratteMap) throws IOException, MalformedFileException {
		if (reader == null)
			throw new MalformedFileException("Reader nullo");
		BufferedReader bReader = new BufferedReader(reader);
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
		DecimalFormat currencyF = new DecimalFormat("¤ #,##0.##");
		List<Servizio> servizi = new ArrayList<>();
		String line;
		while ((line = bReader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, ";");
			if (tokenizer.countTokens() != 5)
				throw new MalformedFileException("Numero di token diverso dalle attese (= 4)");
			String nomeTratta = tokenizer.nextToken().trim();
			String partenzaString = tokenizer.nextToken().trim();
			String arrivoString = tokenizer.nextToken().trim();
			LocalTime arrivo, partenza;
			try {
				arrivo = LocalTime.parse(arrivoString, formatter);
				partenza = LocalTime.parse(partenzaString, formatter);
			} catch (Exception e) {
				throw new MalformedFileException("Formato orario errato");
			}
			String nomeNave = tokenizer.nextToken().trim();
			String costoString = tokenizer.nextToken().trim();
			double costo;
			try {
				costo = currencyF.parse(costoString).doubleValue();
			} catch (ParseException e) {
				throw new MalformedFileException("Formato costo errato");
			}
			Tratta tratta = tratteMap.stream().filter(t -> t.getId().equalsIgnoreCase(nomeTratta)).findFirst().get();
			
			servizi.add(new Servizio(nomeNave, tratta, partenza, arrivo, costo));
	
		}
		
		return servizi;
	}

}
