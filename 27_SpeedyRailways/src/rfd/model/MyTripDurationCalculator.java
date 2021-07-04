package rfd.model;

import java.time.Duration;
import java.util.List;;

public class MyTripDurationCalculator implements TripDurationCalculator {

	private double getSegmentDuration(Segment segment) {
		return (segment.getTo().getKm()-segment.getFrom().getKm())/segment.getFrom().getSpeed();
	}
	
	
	@Override
	public Duration getDuration(Route route) {
		double res = 0.0;
		List<Segment> list = route.getRouteSegments();
		for(Segment segment : list) {
			List<Segment> splitSegments = segment.normalize().split();
			for(Segment miniSegment  : splitSegments)
				res += getSegmentDuration(miniSegment);	
		}
		long totale = (long) Math.round(res*60);
		return Duration.ofMinutes(totale);
	}

}
