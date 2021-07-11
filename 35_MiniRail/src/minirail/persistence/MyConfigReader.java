package minirail.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import minirail.model.Gauge;
import minirail.model.Train;

public class MyConfigReader implements ConfigReader {

	private Gauge gauge;
	private String stringGuage = "gauge";
	private List<Train> trains;
	
	@Override
	public List<Train> getTrains() {
		return trains;
	}

	@Override
	public Gauge getGauge() {
		return gauge;
	}
	
	public MyConfigReader(Reader baseReader) throws BadFileFormatException, IOException {
		if (baseReader == null)
			throw new BadFileFormatException("Reader nullo");
		BufferedReader reader = new BufferedReader(baseReader);
		this.trains = new ArrayList<>();
		String line = reader.readLine();
		String data[] = line.split("\\s+");
		if(!data[1].trim().equalsIgnoreCase(stringGuage))
			throw new BadFileFormatException("Formato riga errato");
		try {
			this.gauge = Gauge.valueOf(data[0].trim());
		} catch (IllegalArgumentException e1) {
			throw new BadFileFormatException("Formato gauge errato");
		}
	
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, ",");
			if (tokenizer.countTokens() != 3)
				throw new BadFileFormatException("Formato riga errato");
			String idTrain = tokenizer.nextToken().trim();
			String lunghezza = tokenizer.nextToken().trim();
			StringTokenizer tokenizer2;
			double len;
			String getLung;
			if (lunghezza.contains("cm")) {
				tokenizer2 = new StringTokenizer(lunghezza, "cm");
				getLung = tokenizer2.nextToken().trim();
				try {
					len = Double.parseDouble(getLung);
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("Formato lunghezza errato");
				}
			}
			else if (lunghezza.contains("in")) {
				tokenizer2 = new StringTokenizer(lunghezza, "in");
				getLung = tokenizer2.nextToken().trim();
				try {
					len = Double.parseDouble(getLung);
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("Formato lunghezza errato");
				}
			}
			else {
				throw new BadFileFormatException("Formato lunghezza errato");
			}
			String velocita = tokenizer.nextToken().trim();
			double vel; 
			String getVel;
			if (velocita.contains("km/h")) {
				tokenizer2 = new StringTokenizer(velocita, "km/h");
				getVel = tokenizer2.nextToken().trim();
				try {
					vel = Double.parseDouble(getVel);
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("Formato lunghezza errato");
				}
			}
			else if (velocita.contains("mph")) {
				tokenizer2 = new StringTokenizer(velocita, "mph");
				getVel = tokenizer2.nextToken().trim();
				try {
					vel = Double.parseDouble(getVel);
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("Formato lunghezza errato");
				}
			}
			else {
				throw new BadFileFormatException("Formato velocita errato");
			}
			
			this.trains.add(new Train(idTrain, len, vel));
		}
	}

}
