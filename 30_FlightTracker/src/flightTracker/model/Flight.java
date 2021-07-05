package flightTracker.model;

import java.time.Duration;
import java.util.List;

public class Flight {

	private String id;
	private List<FlightPos> tracks;
	
	public Flight(String id, List<FlightPos> tracks) {
		if (id == null || id.isBlank())
			throw new IllegalArgumentException("ID del volo assente");
		if (tracks == null || tracks.isEmpty())
			throw new IllegalArgumentException("Lista tratte assente");
		this.id = id;
		this.tracks = tracks;
	}

	public String getId() {
		return id;
	}

	public List<FlightPos> getPositions() {
		return tracks;
	}
	
	public Duration getDuration() {
		FlightPos decollo = this.tracks.get(0);
		FlightPos arrivo = this.tracks.get(this.tracks.size()-1);
		
		return Duration.between(decollo.getTimestamp(), arrivo.getTimestamp());
	}
	
	
}
