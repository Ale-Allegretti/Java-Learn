package flightTracker.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;

public class MyFlightReader implements FlightReader {

	
	@Override
	public Flight readFlight(String id, BufferedReader reader) throws IOException, BadFileFormatException {
		if (reader == null)
			throw new BadFileFormatException("Reader nullo");
		if (id == null || id.isBlank())
			throw new BadFileFormatException("ID nullo");
		String line;
		List<FlightPos> tracks = new ArrayList<>();
		validate(reader.readLine());
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, ";");
			System.out.println(tokenizer.countTokens());
			if (tokenizer.countTokens() < 5)
				throw new BadFileFormatException("Numero di token diverso dalle attese (4)");
			String localTime = tokenizer.nextToken().trim();
			ZonedDateTime UTC;
			try { 
				UTC = ZonedDateTime.parse(localTime, DateTimeFormatter.ISO_DATE_TIME);
			} catch (DateTimeParseException e) {
				throw new BadFileFormatException("Data e ora errati");
			}
			
			
			String latitudeString = tokenizer.nextToken(";,").trim();
			double latitude;
			try {
				latitude = Double.parseDouble(latitudeString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("latitude errata");
			}
			
			
			String longitudeString = tokenizer.nextToken().trim();
			double longitude;
			try {
				longitude = Double.parseDouble(longitudeString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("longitude errata");
			}
			
			
			String altitudeString = tokenizer.nextToken().trim();
			double altitude;
			try {
				altitude = Double.parseDouble(altitudeString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("Altitudine assente");
			}

			String positionString = tokenizer.nextToken().trim();
			double position;
			try {
				position = Double.parseDouble(positionString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("position assente");
			}
			
			String directionString = tokenizer.nextToken().trim();
			int direction;
			try {
				direction = Integer.parseInt(directionString);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException("direction assente");
			}
			
			tracks.add(new FlightPos(UTC, latitude, longitude, altitude, position, direction));
		}
		
		return new Flight(id, tracks);
	}

	
	private static void validate(String stringa) throws BadFileFormatException {
		StringTokenizer tokenizer = new StringTokenizer(stringa, ";\n");
		if (tokenizer.countTokens() < 4)
			throw new BadFileFormatException("Numero di token diverso dalle attese (4)");
		if (!tokenizer.nextToken().trim().equals("UTC"))
			throw new BadFileFormatException("UTC assente");
		if (!tokenizer.nextToken().trim().equals("Position"))
			throw new BadFileFormatException("Position assente");
		if (!tokenizer.nextToken().trim().equals("Altitude"))
			throw new BadFileFormatException("Altitude assente");
		if (!tokenizer.nextToken().trim().equals("Speed"))
			throw new BadFileFormatException("Speed assente");
		String direString = tokenizer.nextToken().trim();
		if (!direString.equals("Direction"))
			throw new BadFileFormatException("Direction assente");
		//System.out.println(direString);
	}
	
}
