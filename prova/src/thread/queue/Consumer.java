package thread.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		while(true) {
			if (queue.remainingCapacity() > 0) {
				System.out.println("E' possibile aggiungere ancora " + queue.remainingCapacity() + " su " + queue.size());
			} else if (queue.remainingCapacity() == 0) {
				String elementoRimosso = queue.remove();

				System.out.println("E' stato rimosso l'elemento " + elementoRimosso);
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
