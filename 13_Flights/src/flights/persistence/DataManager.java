package flights.persistence;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import flights.model.*;

public class DataManager {
	
	private HashMap<String, Aircraft> aircraftMap;
	private HashMap<String, Airport> airportMap;
	private AircraftsReader aircraftsReader;
	private CitiesReader citiesReader;
	private FlightScheduleReader flightScheduleReader;
	private Collection<FlightSchedule> flightSchedules;
	
	
	public DataManager(CitiesReader citiesReader, AircraftsReader aircraftsReader,
			FlightScheduleReader flightScheduleReader) {
		this.citiesReader = citiesReader;
		this.aircraftsReader = aircraftsReader;
		this.flightScheduleReader = flightScheduleReader;
		
		airportMap = new HashMap<String, Airport>();
		aircraftMap = new HashMap<String, Aircraft>();
	}


	public Map<String, Aircraft> getAircraftMap() {
		return aircraftMap;
	}


	public Map<String, Airport> getAirportMap() {
		return airportMap;
	}


	public Collection<FlightSchedule> getFlightSchedules() {
		return flightSchedules;
	}
	
	public void readAll() throws IOException, BadFileFormatException {
		try (Reader reader1 = new FileReader("Cities.txt")) {
			Collection<City> cityReader = citiesReader.read(reader1);
			airportMap.clear();
			for (City c : cityReader) {
				for (Airport a : c.getAirports())
					airportMap.put(a.getCode(), a);
			}
		}
		try (Reader reader2 = new FileReader("Aircrafts.txt")) {
			Collection<Aircraft> airReader = aircraftsReader.read(reader2);
			aircraftMap.clear();
			for (Aircraft a : airReader) {
				aircraftMap.put(a.getCode(), a);
			}
		}
		try (Reader reader3 = new FileReader("FlightSchedule.txt")) {
			this.flightSchedules = flightScheduleReader.read(reader3, airportMap, aircraftMap);
		}
		
	}
	
	

}
