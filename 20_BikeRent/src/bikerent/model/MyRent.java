package bikerent.model;

import java.time.Duration;
import java.time.LocalDateTime;


public class MyRent implements Rent {

	private LocalDateTime inizio;
	private LocalDateTime fine;
	Rate rate;
	
	public MyRent(LocalDateTime inizio, LocalDateTime fine, Rate rate) {
		this.inizio = inizio;
		this.fine = fine;
		this.rate = rate;
	}
	
	@Override
	public boolean isRegular() {
		if (this.rate.getOrarioMax().isPresent())
			return this.getEnd().toLocalTime().compareTo(this.rate.getOrarioMax().get()) <= 0 &&
				   this.getEnd().toLocalDate().isEqual(inizio.toLocalDate());
		if (this.rate.getDurataMax().isPresent()) {
			Duration duration = Duration.between(inizio, fine);
			return duration.compareTo(this.rate.getDurataMax().get()) <= 0;
		}
		return false;
	}

	@Override
	public LocalDateTime getStart() {
		return inizio;
	}

	@Override
	public LocalDateTime getEnd() {
		return fine;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyRent [inizio=");
		builder.append(inizio);
		builder.append(", fine=");
		builder.append(fine);
		builder.append(", rate=");
		builder.append(rate);
		builder.append("]");
		return builder.toString();
	}
	
	public String toStringDuration(Duration duration) {
		StringBuilder builder = new StringBuilder();
		builder.append("MyRent [Inizio: ");
		builder.append(inizio);
		builder.append(", Fine: ");
		builder.append(fine);
		builder.append(", Durata: ");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}

	

}
