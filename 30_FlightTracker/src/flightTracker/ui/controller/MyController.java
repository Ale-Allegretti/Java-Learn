package flightTracker.ui.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import flightTracker.model.Flight;
import flightTracker.model.FlightPos;
import flightTracker.model.Point;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.persistence.FlightReader;

public class MyController extends Controller {

	public MyController(String[] availableFlightFiles) {
		super(availableFlightFiles);
	}

	@Override
	public List<Point> getPoints(Flight flight) {
		List<Point> listPoints = new ArrayList<>();
		List<FlightPos> list = flight.getPositions();
		for (FlightPos flightPos : list) 
			listPoints.add(new Point(flightPos.getPosition().getX(), flightPos.getPosition().getY()));

		return listPoints;
	}

	@Override
	public Flight load(String flightId, Reader reader) throws IOException, BadFileFormatException {
		return FlightReader.of().readFlight(flightId, new BufferedReader(reader));
	}
	
	
	
	
	
}
