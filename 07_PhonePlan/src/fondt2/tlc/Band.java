package fondt2.tlc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import fondt2.tlc.util.DayOfWeekHelper;

public class Band {
	
	private DayOfWeek[] combinedDays;
	private LocalTime startTime;
	private LocalTime endTime;
	private double costPerInterval;
	
	public Band(LocalTime start, LocalTime end, DayOfWeek[] combinedDays, double costPerInterval) {
		super();
		this.combinedDays = Arrays.copyOf(combinedDays, combinedDays.length);
		this.startTime = start;
		this.endTime = end;
		this.costPerInterval = costPerInterval;
	}

	public DayOfWeek[] getCombinedDays() {
		return combinedDays;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public double getCostPerInterval() {
		return costPerInterval;
	}
	
	public boolean isInBand(LocalDateTime dateTime) {
		DayOfWeek theDay = dateTime.getDayOfWeek();
		LocalTime theTime = dateTime.toLocalTime();
		if(DayOfWeekHelper.isDayIn(theDay, this.combinedDays) 
				&& this.startTime.isBefore(theTime) && this.endTime.isAfter(theTime))
					return true;
		return false;
	}
	

	public boolean isValid() {
		if (combinedDays != null && combinedDays.length > 0 && startTime != null && endTime != null && costPerInterval >= 0) {
		for (DayOfWeek d : this.combinedDays)
			if (d != null && this.startTime.isBefore(this.endTime))
					return true;
		}
		return false;
	}
	
}


