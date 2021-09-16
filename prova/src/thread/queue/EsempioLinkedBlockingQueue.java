package thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EsempioLinkedBlockingQueue {
	public static void main(String[] args) {
		// Creo una coda senza specificare la capacità.
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

        // Producer e Consumer accedono alla stessa coda...
        Thread prod = new Thread(new Producer(queue));
        Thread cons = new Thread(new Consumer(queue));

        prod.start();
        cons.start();
	}
}
