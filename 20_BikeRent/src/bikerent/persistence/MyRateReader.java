package bikerent.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import bikerent.model.Periodo;
import bikerent.model.Rate;


public class MyRateReader implements RateReader {

	
	@Override
	public Map<String, Rate> readRates(Reader reader) throws IOException, BadFileFormatException {
		Map<String,Rate> mappaTariffe = new HashMap<>();
		Rate rate;
		BufferedReader fin = new BufferedReader(reader);
		while ((rate = readOneRate(fin)) != null) {
			mappaTariffe.put(rate.getCitta(), rate);
		}	
		return mappaTariffe;
	}
	
	private Rate readOneRate(BufferedReader reader) throws BadFileFormatException {
		String line;
		try {			
			while ((line = reader.readLine()) != null) {
				String[] items = line.split(",");
				String citta = items[0].trim();
				Periodo primo = estraiPrimoPeriodo(items[1].trim());
				Periodo successivi = estraiSuccessiviPeriodi(items[2].trim());
				Optional<Duration> durataMax = estraiDurataMax(items[3].trim());
				Optional<LocalTime> orarioMax = estraiOrarioMax(items[3].trim());
				double sanzione = estraiSanzione(items[4].trim());
				return new Rate(citta, primo, successivi, durataMax, orarioMax, sanzione);
			}
		}
		catch (IOException | NumberFormatException e)
		{
			throw new BadFileFormatException(e);
		}
		return null;
		
	}
	
	private Optional<Duration> estraiDurataMax(String s) throws BadFileFormatException {
		String[] items = s.split("\\s+");
		if (items.length != 3) 
			throw new BadFileFormatException("formato errato, sono richiesti tre campi");
		if(!items[2].equals("ore"))
			return Optional.empty();
		if(!items[0].equals("max"))
			throw new BadFileFormatException("manca la keyword 'max'");
		
		return Optional.of(Duration.ofHours(Integer.parseInt(items[1])));
	} 
	
	private Optional<LocalTime> estraiOrarioMax(String s) throws BadFileFormatException {
		String[] items = s.split("\\s+");
		if (items.length != 3) 
			throw new BadFileFormatException("formato errato, sono richiesti tre campi");
		if(!items[1].equals("entro"))
			return Optional.empty();
		if(!items[0].equals("max"))
			throw new BadFileFormatException("manca la keyword 'max'");
		return Optional.of(LocalTime.parse(items[2]));
	}
	
	private Periodo estraiPrimoPeriodo(String s) throws BadFileFormatException {
		String[] items = s.split("\\s+");
		return estraiPeriodo(items, 0);
	}
	
	private Periodo estraiPeriodo(String[] items, int index) throws BadFileFormatException {
		if (items.length-index != 5) 
			throw new BadFileFormatException("sono richiesti cinque campi, non " + (items.length-index));
		if (!items[index + 1].equalsIgnoreCase("cent")) 
			throw new BadFileFormatException("manca la keyword 'cent'");
		if (!items[index + 2].equalsIgnoreCase("per"))
			throw new BadFileFormatException("manca la keyword 'per'");
		if (!items[index + 4].equalsIgnoreCase("minuti")) 
			throw new BadFileFormatException("manca la keyword 'minuti'");
		return new Periodo(Integer.parseInt(items[index+0]), Duration.ofMinutes(Integer.parseInt(items[index+3])));
		
		
	}
	
	private Periodo estraiSuccessiviPeriodi(String s) throws BadFileFormatException {
		String[] items = s.split("\\s+");
		if (items[0].equalsIgnoreCase("poi"))
			return estraiPeriodo(items, 1);
		else 
			throw new BadFileFormatException("manca la keyword 'max'");
	}
	
	private double estraiSanzione(String s) throws BadFileFormatException {
		String[] items = s.split("\\s+");
		if (items.length!=3) 
			throw new BadFileFormatException("formato errato, sono richiesti tre campi");
		if (!items[0].equalsIgnoreCase("sanzione"))
			throw new BadFileFormatException("manca la keyword 'sanzione'");
		if (!items[2].equalsIgnoreCase("euro"))
			throw new BadFileFormatException("manca la keyword 'euro'");
		
		return Double.parseDouble(items[1]);
	}
	

}
