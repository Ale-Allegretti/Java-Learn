package rfd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DirectRouteFinder extends RouteFinder {

	public DirectRouteFinder(Set<RailwayLine> rwylines) {
		super(rwylines);
	}

	@Override
	public List<Route> getRoutes(String from, String to) {
		if (from == null || to == null)
			throw new IllegalArgumentException("Nomi stazioni nulle");
		List<Route> routes = new ArrayList<>();
		for (RailwayLine railwayLine : this.rwylines) {
			Optional<PointOfInterest> tempFrom = railwayLine.getPointOfInterest(from);
			Optional<PointOfInterest> tempTo = railwayLine.getPointOfInterest(to);
			if (tempFrom.isPresent() && tempTo.isPresent() && !tempFrom.get().equals(tempTo.get())) {
				routes.add(new Route(new Segment(railwayLine.getPointOfInterest(from).get(), 
					railwayLine.getPointOfInterest(to).get())));
			}
		}
		return routes;
	}

	
}
