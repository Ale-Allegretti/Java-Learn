package zs.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shipment {
	
	private String tracking;
	private Recipient recipient;
	private float weightInKg;
	List<Delivery> deliveries;
	
	public Shipment(String tracking, Recipient recipient, float weightInKg) {
		if (tracking == null || recipient == null || tracking.isEmpty() || weightInKg <= 0)
			throw new IllegalArgumentException("Valori non definibili");
		this.tracking = tracking;
		this.recipient = recipient;
		this.weightInKg = weightInKg;
		this.deliveries = new ArrayList<>();
	}

	public String getTracking() {
		return tracking;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public float getWeightInKg() {
		return weightInKg;
	}
	
	public List<Delivery> getDeliveries(){
		 return this.deliveries;
	}
	
	public boolean isDelivered() {
		return deliveries.size() > 0 && 
		   deliveries.get(deliveries.size() - 1) instanceof SucceededDelivery;
		}
	
	private void checkNotDelivered() {
		if (isDelivered())
			throw new InvalidOperationException("Already delivered");
	}
				
	public SucceededDelivery createSucceededDelivery(LocalDateTime dateTime, String notes, String recipientString) {
		SucceededDelivery succeededDelivery;
		if (dateTime == null || recipientString == null || recipientString.isEmpty())
			throw new IllegalArgumentException("Spedizione non definibile");
		succeededDelivery = new SucceededDelivery(dateTime, notes, recipientString);
		checkNotDelivered();
		this.deliveries.add(succeededDelivery);
		return succeededDelivery;
	}
	
	public FailedDelivery createFailedDelivery(LocalDateTime dateTime, String notes) {
		FailedDelivery failedDelivery;
		if (dateTime == null)
			throw new IllegalArgumentException("Spedizione non definibile");
		failedDelivery = new FailedDelivery(dateTime, notes);
		checkNotDelivered();
		this.deliveries.add(failedDelivery);
		return failedDelivery;
	}

	@Override
	public String toString() {
		return recipient.toString();
	}
	
	
}
	
	
	

