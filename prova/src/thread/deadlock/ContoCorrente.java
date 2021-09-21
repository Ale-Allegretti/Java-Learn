package thread.deadlock;

/**
 * Si ha il deadlock perché:
 *  - il thread t1 aspetta che il thread t2 rilasci il lock sul conto2
 *  - il thread t2 aspetta che il thread t1 rilasci il lock sul conto1
 * 
 */
public class ContoCorrente {
	private double saldo;
	private int id;

	public ContoCorrente(int id, double saldo) {
		setId(id);
		setSaldo(saldo);
	}

	public void preleva(double importo) {
		try {
			Thread.sleep(4000); /* metto il thread in attesa per simulare l'accesso al db per il prelievo */
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Prelevo " + importo + " dal conto " + getId());
		setSaldo(getSaldo() - importo);
	}

	public void versa(double importo) {
		try {
			Thread.sleep(4000); /* metto il thread in attesa per simulare l'accesso al db per il prelievo */
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Verso " + importo + " sul conto " + getId());
		setSaldo(getSaldo() + importo);
	}

	public static void trasferisci(ContoCorrente da, ContoCorrente a, double importo) {
		System.out.println("Sposto i soldi dal conto " + da.id + " al conto " + a.id);
		
		synchronized(da) {
			System.out.println("Avvio il prelievo da " + da.id);
			da.preleva(importo);
			
			synchronized(a) {
				System.out.println("Avvio il versamento su " + a.id);
				a.versa(importo);
			}
		}
	}

	public static void main(String[] args) {
		final ContoCorrente conto1 = new ContoCorrente(1, 500d);
		final ContoCorrente conto2 = new ContoCorrente(2, 500d);

		Thread t1 = new Thread() {
			public void run() {
				ContoCorrente.trasferisci(conto2, conto1, 25d);
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				ContoCorrente.trasferisci(conto1, conto2, 30d);
			}
		};
		
		t1.start();		// rimane in stallo perché entrambi aspettano che l'altro sblocchi il thread
		t2.start();
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}