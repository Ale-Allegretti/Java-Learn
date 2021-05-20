package flights.persistence;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;


import flights.model.Aircraft;
import flights.model.Airport;
import flights.model.FlightSchedule;

public class MyFlightScheduleReader implements FlightScheduleReader {
	
	private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);

	@Override
	public Collection<FlightSchedule> read(Reader reader, Map<String, Airport> airportMap,
			Map<String, Aircraft> aircraftMap) throws IOException, BadFileFormatException {
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		ArrayList<FlightSchedule> schedules = new ArrayList<FlightSchedule>();
		
		FlightSchedule schedule;
		while ((schedule = readSchedule(bufferedReader, airportMap, aircraftMap)) != null)
			schedules.add(schedule);
	
		return schedules;
	}
	
	private Collection<DayOfWeek> readDaysOfWeek(String day) throws BadFileFormatException {
		if (day.length() != 7)
				throw new BadFileFormatException();
		
		ArrayList<DayOfWeek> res = new ArrayList<DayOfWeek>();
		for (int i = 0; i < 7; i++) {
			char c = day.charAt(i);
			if (c == '1' + i)
				res.add(DayOfWeek.of(c - '0'));
			else if (c != '-')
				throw new BadFileFormatException("Bad Day Format");
		}
		return res;
	}
	
	private FlightSchedule readSchedule(BufferedReader reader, Map<String, Airport> airportMap, 
			Map<String, Aircraft> aircraftMap) throws BadFileFormatException, IOException {
		String line = reader.readLine();
		if (line == null || line.trim().isEmpty())
			return null;

		StringTokenizer tokenizer = new StringTokenizer(line, ",");
		
		try {
			String code = tokenizer.nextToken();
			String token = tokenizer.nextToken();
			Airport departureAirport = airportMap.get(token);
			if (departureAirport == null)
				throw new BadFileFormatException("Departure airport not present: " + token);
			
			token = tokenizer.nextToken();
			LocalTime departureLocalTime = LocalTime.parse(token, timeFormatter);
			
			token = tokenizer.nextToken();
			Airport arrivalAirport = airportMap.get(token);
			if (arrivalAirport == null)
				throw new BadFileFormatException("Arrival airport not present: " + token);
			
			token = tokenizer.nextToken();
			LocalTime arrivalLocalTime = LocalTime.parse(token, timeFormatter);
			
			token = tokenizer.nextToken();
			int dayOffset = Integer.parseInt(token);
			
			token = tokenizer.nextToken();
			Collection<DayOfWeek> daysOfWeek = readDaysOfWeek(token);
			
			token = tokenizer.nextToken();
			Aircraft aircraft = aircraftMap.get(token);
			if (aircraft == null)
				throw new BadFileFormatException("Aircraft not present: " + token);
			
			FlightSchedule schedule = new FlightSchedule(code, departureAirport, departureLocalTime, arrivalAirport, arrivalLocalTime, dayOffset,
					daysOfWeek, aircraft);
			return schedule;
		}
		catch (BadFileFormatException e){
			throw e;
		}
		catch (Exception e) {
			throw new BadFileFormatException(e);
		}
		
	}
	

}
