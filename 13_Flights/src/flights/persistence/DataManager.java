package flights.persistence;

import java.io.FileNotFoundException;
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
	
	public void readAll() throws FileNotFoundException, IOException, BadFileFormatException {
		try (Reader reader1 = new FileReader("Cities.txt")) {
			Collection<City> cityReader = citiesReader.read(reader1);
			this.airportMap = new HashMap<String, Airport>();
			for (City c : cityReader) {
				for (Airport a : c.getAirports())
					this.airportMap.put(a.getCode(), a);
			}
		}
		try (Reader reader2 = new FileReader("Aircrafts.txt")) {
			Collection<Aircraft> airReader = aircraftsReader.read(reader2);
			this.aircraftMap = new HashMap<String, Aircraft>();
			for (Aircraft a : airReader) {
				this.aircraftMap.put(a.getCode(), a);
			}
		}
		try (Reader reader3 = new FileReader("FlightSchedule.txt")) {
			this.flightSchedules = flightScheduleReader.read(reader3, airportMap, aircraftMap);
		}
		
		catch (FileNotFoundException e) {
			throw e;
		}
	}
	
	

}
