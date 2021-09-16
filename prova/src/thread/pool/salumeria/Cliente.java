package thread.pool.salumeria;

import java.util.Random;

public class Cliente implements Runnable {
	private int numeroTicket;

	public Cliente(int numeroTicket) {
		System.out.println("E' arrivato un nuovo cliente ed ha preso il numero " + numeroTicket);

		this.numeroTicket = numeroTicket;
	}

	public void run() {
		/* il cliente ordina i prodotti al dipendente presente al bancone */
		richiediProdotti();
	}

	private void richiediProdotti() {
		System.out.println("Viene servito il cliente numero " + numeroTicket);

		/* imposto una durata random per ciascun cliente... */
		Random r = new Random();

		/* per semplicità ipotizzo che ogni cliente impieghi tra 5 e 20 secondi per acquistare salumi e formaggi */
		int tempoImpiegatoPerAcquisto = (r.nextInt(15) + 5)*1000;

		try {
			/* 
			 * il thread viene sospeso per tempoImpiegatoPerAcquisto millisecondi.
			 * Quest'attesa equivale al cliente che sta effettuando l'ordine al dipendente del bancone 
			 */
			Thread.sleep(tempoImpiegatoPerAcquisto);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Il cliente che aveva il numero " + numeroTicket + " ha completato il suo acquisto in " + tempoImpiegatoPerAcquisto/1000 + " secondi");
	}
}