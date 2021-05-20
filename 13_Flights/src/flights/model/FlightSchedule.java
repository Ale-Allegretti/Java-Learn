package flights.model;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;;

public class FlightSchedule {
	private Aircraft aircraft;
	private Airport arrivalAirport;
	private LocalTime arrivalLocalTime;
	private String code;
	private int dayOffset;
	private Set<DayOfWeek> daysOfWeek;
	private Airport departureAirport;
	private LocalTime departureLocalTime;
	
	
	public FlightSchedule(String code, Airport departureAirport, LocalTime departureLocalTime, Airport arrivalAirport,
			LocalTime arrivalLocalTime, int dayOffset, Collection<DayOfWeek> daysOfWeek, Aircraft aircraft) {
		this.code = code;
		this.departureAirport = departureAirport;
		this.departureLocalTime = departureLocalTime;
		this.arrivalAirport = arrivalAirport;
		this.arrivalLocalTime = arrivalLocalTime;
		this.dayOffset = dayOffset;
		this.aircraft = aircraft;
		this.daysOfWeek = new TreeSet<DayOfWeek>(daysOfWeek);
	}


	public Aircraft getAircraft() {
		return aircraft;
	}


	public Airport getArrivalAirport() {
		return arrivalAirport;
	}


	public LocalTime getArrivalLocalTime() {
		return arrivalLocalTime;
	}


	public String getCode() {
		return code;
	}


	public int getDayOffset() {
		return dayOffset;
	}


	public Set<DayOfWeek> getDaysOfWeek() {
		return daysOfWeek;
	}


	public Airport getDepartureAirport() {
		return departureAirport;
	}


	public LocalTime getDepartureLocalTime() {
		return departureLocalTime;
	}
	
	public Duration getFlightDuration() {
		OffsetDateTime departure = OffsetDateTime.of(LocalDate.now(),departureLocalTime,
				ZoneOffset.ofHours(getDepartureAirport().getCity().getTimeZone()));
		OffsetDateTime arrival = OffsetDateTime.of(LocalDate.now().plusDays(getDayOffset()),
				arrivalLocalTime, ZoneOffset.ofHours(getArrivalAirport().getCity().getTimeZone()));
		
		Duration res = Duration.between(departure, arrival);
		
		if (res.isNegative())
			return res.plusHours(24);
		return res;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraft == null) ? 0 : aircraft.hashCode());
		result = prime * result + ((arrivalAirport == null) ? 0 : arrivalAirport.hashCode());
		result = prime * result + ((arrivalLocalTime == null) ? 0 : arrivalLocalTime.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + dayOffset;
		result = prime * result + ((daysOfWeek == null) ? 0 : daysOfWeek.hashCode());
		result = prime * result + ((departureAirport == null) ? 0 : departureAirport.hashCode());
		result = prime * result + ((departureLocalTime == null) ? 0 : departureLocalTime.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FlightSchedule))
			return false;
		FlightSchedule other = (FlightSchedule) obj;
		if (aircraft == null) {
			if (other.aircraft != null)
				return false;
		} else if (!aircraft.equals(other.aircraft))
			return false;
		if (arrivalAirport == null) {
			if (other.arrivalAirport != null)
				return false;
		} else if (!arrivalAirport.equals(other.arrivalAirport))
			return false;
		if (arrivalLocalTime == null) {
			if (other.arrivalLocalTime != null)
				return false;
		} else if (!arrivalLocalTime.equals(other.arrivalLocalTime))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dayOffset != other.dayOffset)
			return false;
		if (daysOfWeek == null) {
			if (other.daysOfWeek != null)
				return false;
		} else if (!daysOfWeek.equals(other.daysOfWeek))
			return false;
		if (departureAirport == null) {
			if (other.departureAirport != null)
				return false;
		} else if (!departureAirport.equals(other.departureAirport))
			return false;
		if (departureLocalTime == null) {
			if (other.departureLocalTime != null)
				return false;
		} else if (!departureLocalTime.equals(other.departureLocalTime))
			return false;
		return true;
	}
	
	
	
	

}
