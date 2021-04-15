package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;


import fondt2.tlc.util.DayOfWeekHelper;

public class Rate {
	
	private Band[] bands;
	private int intervalInMillis;
	private String name;
	private String numberRoot;
	private double startCallCost;
	
	public Rate(String name, Band[] bands, int intervalInMillis, double startCallCost, String numberRoot ) {
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
		int intervalli = (int) Math.ceil((double)Duration.between(call.getStart(), call.getEnd()).toMillis() / this.intervalInMillis);
		for(Band band: this.bands)
			if(band.isInBand(call.getStart()))
				return this.startCallCost + (intervalli * band.getCostPerInterval());
		return -1.0;
	}
	
	
	public boolean isApplicableTo(String destinationNumber) {
		if(destinationNumber.startsWith(this.numberRoot))
			return true;
		return false;
	}
	
	public boolean isValid() {
		for(Band b : this.bands) 
			if(!b.isValid())
				return false;
		for (DayOfWeek day : DayOfWeek.values()) {
	           if (!validateDay(day)) {
	               return false;
	           }
	       }
	    return true;	
	}
	
	private boolean validateDay(DayOfWeek day) {
		Band[] estratte = this.selectBandsInDay(day);
		sortBandsByStartTime(estratte);
		if(validateBandsInDay(estratte))
			return true;
		else return false;
	}
	
	private Band[] selectBandsInDay(DayOfWeek day) {
        int count = 0;
        for (Band band : bands) {
            if (DayOfWeekHelper.isDayIn(day, band.getCombinedDays())) {
                count++;
            }
        }
        Band[] result = new Band[count];
        int j = 0;
        for(int i = 0; i < this.bands.length; i++)
        	if (DayOfWeekHelper.isDayIn(day, this.bands[i].getCombinedDays())) {
        		result[j] = this.bands[i];
        		j++;
        	}
        return result;
	}
	
	
	private void sortBandsByStartTime(Band[] bands) {	//bubble sort adattato
		boolean ordinato = false;
		int size_v = bands.length - 1;
		while (size_v > 1 && !ordinato) {
			ordinato = true;
			for (int i = 0; i < size_v - 1; i++)
				if (bands[i].getStartTime().isAfter(bands[i + 1].getStartTime())) {
					Band temp = bands[i];
					bands[i] = bands[i+1];
					bands[i+1] = temp;
					ordinato = false;
				}
			size_v--;
		}
	}
	
	
	private boolean validateBandsInDay(Band bands[]) {
		if(bands[0].getStartTime() != LocalTime.MIN || bands[bands.length-1].getEndTime() != LocalTime.MAX ) {
			return false;
		}
		
		for(int i=1; i < bands.length - 2; i++){  
            if(bands[i].getStartTime() != bands[i-1].getEndTime().plusNanos(1)) {
            	return false;
            }
        }  
		return true;
	}
	
	
}
