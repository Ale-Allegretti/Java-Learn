package fondt2.tlc;

import java.time.LocalDateTime;

public class Rate {
	
	private Band[] bands;
	private int intervalInMillis;
	private String name;
	private String numberRoot;
	private double startCallCost;
	
	public Rate(String name, Band[] bands, int intervalInMillis, double startCallCost, String numberRoot ) {
		super();
		this.bands = bands;
		this.intervalInMillis = intervalInMillis;
		this.name = name;
		this.numberRoot = numberRoot;
		this.startCallCost = startCallCost;
	}

	public Band[] getBands() {
		return bands;
	}

	public String getName() {
		return name;
	}

	public double getCallCost(PhoneCall call) {
		return 0;
	}
	
	public double getCostPerIntervall(LocalDateTime dateTime) {
		return 0;	
	}
	
	public boolean isApplicableTo(String destinationNumber) {
		if(destinationNumber.startsWith(numberRoot))
			return true;
		return false;
	}
	
	

	
}
