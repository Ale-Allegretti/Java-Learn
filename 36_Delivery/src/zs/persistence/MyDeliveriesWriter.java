package zs.persistence;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import zs.model.Delivery;
import zs.model.FailedDelivery;
import zs.model.Shipment;
import zs.model.SucceededDelivery;

public class MyDeliveriesWriter implements DeliveriesWriter {

	private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	@Override
	public void write(BufferedWriter writer, Collection<Shipment> collection) throws IOException {
		String wString;
		for (Shipment ship : collection) {
			for (Delivery delivery : ship.getDeliveries()) {
			wString = delivery instanceof SucceededDelivery ?
				"Succeeded;" + ship.getTracking() + ";" + formatter.format(delivery.getDateTime()) 
				+ ";" + ((SucceededDelivery) delivery).getRecipientSign() + ";" + delivery.getNotes()
				: delivery instanceof FailedDelivery ?
				"Failed;" + ship.getTracking() + ";" + formatter.format(delivery.getDateTime()) 
				+ ";" + delivery.getNotes()
				: "";
			if (wString.isEmpty())
				throw new IllegalArgumentException("Delivery unknown");
			writer.write(wString);
			writer.newLine();
			}
		}
	}

}
