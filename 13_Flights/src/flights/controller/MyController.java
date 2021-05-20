package flights.controller;

import java.time.LocalDate;
import java.util.*;

import flights.model.*;
import flights.persistence.*;

public class MyController implements Controller {
	
	private DataManager dataManager;
	private List<Airport> sortedAirports;

	public MyController(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	
	@Override
	public List<Airport> getSortedAirports() {
		this.sortedAirports = new ArrayList<>(this.dataManager.getAirportMap().values());
		sortedAirports.sort(Comparator.comparing((Airport a1) -> a1.getCity().getName()).thenComparing(a1 -> a1.getName()));
		
		return sortedAirports;
	}

	@Override
	public List<FlightSchedule> searchFlights(Airport departureAirport, Airport arrivalAirport, LocalDate date) {
		List<FlightSchedule> res = new ArrayList<FlightSchedule>();
		List<FlightSchedule> temp = new ArrayList<FlightSchedule>(this.dataManager.getFlightSchedules());
		
		for (FlightSchedule fs : temp) {
			if (fs.getArrivalAirport().equals(arrivalAirport) &&
				fs.getDepartureAirport().equals(departureAirport) &&
				fs.getDaysOfWeek().contains(date.getDayOfWeek()))
				res.add(fs);
		}
		
		return res;
	}

	

}
