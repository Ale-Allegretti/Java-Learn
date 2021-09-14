package thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEsempio {
	private ReentrantLock istanzaLock = new ReentrantLock();
	private int contatore = 0;
	private int somma = 0;
	
	public int conta() {
		System.out.println("Il thread " + Thread.currentThread().getName() + " ha richiesto di incrementare il contatore");
		
		istanzaLock.lock();

		try {
			System.out.println(Thread.currentThread().getName() + " contatore = " + contatore);
			contatore++;

			return contatore;
		} finally {
			istanzaLock.unlock();
		}
	}
	
	public void somma() {
		System.out.println("Il thread " + Thread.currentThread().getName() + " ha richiesto di visualizzare la somma dei contatori");
		
		if(istanzaLock.tryLock()) {
			try {
				somma += contatore;
				System.out.println(Thread.currentThread().getName() + " la somma vale = " + somma);
			} finally {
				istanzaLock.unlock();
			}
		} else {
			System.out.println("************************ Il thread che ha il lock sull'oggetto �: " + Thread.currentThread().getName());
		}
	}
}

