package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import fondt2.tlc.util.DayOfWeekHelper;

public class Band {
	
	private DayOfWeek[] combinedDays;
	private LocalTime start;
	private LocalTime end;
	private double costPerInterval;
	
	public Band(LocalTime start, LocalTime end, DayOfWeek[] combinedDays, double costPerInterval) {
		super();
		this.combinedDays = combinedDays;
		this.start = start;
		this.end = end;
		this.costPerInterval = costPerInterval;
	}

	public DayOfWeek[] getCombinedDays() {
		return combinedDays;
	}

	public LocalTime getStartTime() {
		return start;
	}

	public LocalTime getEndTime() {
		return end;
	}

	public double getCostPerInterval() {
		return costPerInterval;
	}
	
	public boolean isInBand(LocalDateTime dateTime) {
		DayOfWeek theDay = dateTime.getDayOfWeek();
		LocalTime theTime = LocalTime.of(dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
		if(DayOfWeekHelper.isDayIn(theDay, this.combinedDays) && this.isValid() 
				&& this.start.isBefore(theTime) && this.end.isAfter(theTime))
					return true;
		return false;
	}
	

	public boolean isValid() {
		for (DayOfWeek d : this.combinedDays)
			if (d != null && this.start.isBefore(this.end) && this.combinedDays.length > 0 && this.costPerInterval >= 0)
					return true;
		return false;
	}
	
}


