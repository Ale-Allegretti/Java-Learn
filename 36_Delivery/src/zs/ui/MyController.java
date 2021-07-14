package zs.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import zs.model.Shipment;
import zs.persistence.ShipmentRepository;

public class MyController implements Controller{

	private UserInteractor userInteractor;
	private ShipmentRepository repository;
	
	public MyController(ShipmentRepository repository, UserInteractor userInteractor) {
		this.repository = repository;
		this.userInteractor = userInteractor;
	}
	
	@Override
	public void markFailedDelivery(Shipment shipment) {
		String notes = userInteractor.requestUserNotes();
		shipment.createFailedDelivery(LocalDateTime.now(), notes);
		try {
			repository.update(shipment);
		} catch (IOException e) {
			userInteractor.somethingWentWrongWhileSaving(e.getMessage());
		}
	}

	@Override
	public void markSucceededDelivery(Shipment shipment) {
		String notes = userInteractor.requestUserNotes();
		String recipientSign = userInteractor.requestRecipientSign();
		shipment.createSucceededDelivery(LocalDateTime.now(), notes, recipientSign);
		try {
			repository.update(shipment);
		} catch (IOException e) {
			userInteractor.somethingWentWrongWhileSaving(e.getMessage());
		}
	}
	
	@Override
	public List<Shipment> getNonDelivered() {
		return repository.getNonDelivered();
	}
	
	@Override
	public List<Shipment> getDelivered() {
		return repository.getDelivered();
	}

}
