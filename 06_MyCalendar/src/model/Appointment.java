package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Appointment {
	
	private LocalDateTime from;
	private LocalDateTime to;
	private String description;
	
	public Appointment(String description, LocalDateTime from, LocalDateTime to) {
		super();
		this.from = from;
		this.to = to;
		this.description = description;
	}
	
	public Appointment(String description, LocalDateTime from, Duration duration) {
		this(description, from, from.plus(duration));
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Duration getDuration() {
		return Duration.between(this.from, this.to);
	}

	public void setDuration(Duration duration) {
		this.to = getFrom().plus(duration); 
	}
	
	public boolean equals(Appointment app) {
		if (this.description.equalsIgnoreCase(app.getDescription())
				&& this.from.equals(app.getFrom()) && this.to.equals(app.getTo()))
			return true;
		else 
			return false;
	}


	@Override
	public String toString() {
		return "Appointment [from=" + from + ", to=" + to + ", description=" + description + "]";
	}
	
	

}
