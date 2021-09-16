package thread.pool.salumeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BanconeSalumeriaFormaggi {
	private final static int CLIENTI_DA_SERVIRE = 30;
	private final int DIPENDENTI_AL_BANCONE = 3;
	
	private BlockingQueue<Runnable> codaBancone = new ArrayBlockingQueue<Runnable>(30, true);
	private ExecutorService dipendentiDisponibili = Executors.newFixedThreadPool(DIPENDENTI_AL_BANCONE);

	public static void main(String[] args) {
		System.out.println("Nel supermercato ci sono " + CLIENTI_DA_SERVIRE + " clienti che stanno andando al bancone");

		BanconeSalumeriaFormaggi bancone = new BanconeSalumeriaFormaggi();
		bancone.arrivoClientiAlBancone();
		bancone.servizioClienti();
	}

	private void arrivoClientiAlBancone() {
		for (int i = 1; i <= CLIENTI_DA_SERVIRE; i++) {
			try {
				/* il cliente viene inserito in coda */
				codaBancone.put(new Cliente(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void servizioClienti() {
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						/* il primo cliente disponibile viene servito ... */
						dipendentiDisponibili.execute(codaBancone.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
