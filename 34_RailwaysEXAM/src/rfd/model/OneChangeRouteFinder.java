package rfd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class OneChangeRouteFinder extends RouteFinder{

	public OneChangeRouteFinder(Set<RailwayLine> rwylines) {
		super(rwylines);
	}

	@Override
	public List<Route> getRoutes(String from, String to) {
        List<Route> percorsi = new ArrayList<>();
        Set<RailwayLine> stazA = getLinesAtStation(from);
        Set<RailwayLine> stazB = getLinesAtStation(to);
        DirectRouteFinder drA = new DirectRouteFinder(stazA);
        DirectRouteFinder drB = new DirectRouteFinder(stazB);
        for (RailwayLine rail : stazA) {
            if (stazB.contains(rail)) { // percorso diretto
                continue;
            }
            for (String interscambio : rail.getTransferPoints()) {
                List<Route> percorsiTB = drB.getRoutes(interscambio, to);
                if (percorsiTB.isEmpty()) {
                    continue;
                }
                List<Route> percorsiAT = drA.getRoutes(from, interscambio);
                if (percorsiAT.isEmpty()) {
                    continue;
                }
                Segment AT = percorsiAT.get(0).getRouteSegments().get(0);
                Segment TB = percorsiTB.get(0).getRouteSegments().get(0);
                percorsi.add(new Route(AT, TB));
            }
        }
        return percorsi;
    }
}
