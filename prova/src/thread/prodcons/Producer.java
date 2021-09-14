package thread.prodcons;

import java.util.List;

public class Producer implements Runnable {
	private final List<Integer> bufferCondiviso;
	private final int SIZE;
	private int i = 1;
	
	public Producer(List<Integer> bufferCondiviso, int size) {
		this.bufferCondiviso = bufferCondiviso;
		this.SIZE = size;
	}

	@Override
	public void run() {
		while(true) {
			try {
				produce();
				i++;
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void produce() throws InterruptedException {
		// il thread resta in stato wait se il buffer � pieno
		while (bufferCondiviso.size() == SIZE) {
			synchronized (bufferCondiviso) {
				System.out.println("Il buffer � pieno, il thread Producer resta in attesa... "
						+ "la dimensione del buffer adesso �: " + bufferCondiviso.size());

				bufferCondiviso.wait();
			}
		}

		// il buffer non � pieno, quindi il thread pu� aggiungere un nuovo elemento e notificarlo al consumer
		synchronized (bufferCondiviso) {
			bufferCondiviso.add(i);
			bufferCondiviso.notifyAll();
			
			System.out.println("Il thread Producer ha aggiunto al buffer l'elemento: " + i 
						+ " la dimensione del buffer adesso �: " + bufferCondiviso.size());
		}
	}
}