package thread.sync;

public class ClienteNonSync extends Thread {
	private double sommaDaPrelevare;
	
	public ClienteNonSync(String nomeCliente, double sommaDaPrelevare) {
		super();
		this.setName(nomeCliente);
		this.sommaDaPrelevare = sommaDaPrelevare;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " arriva al bancomat");
		System.out.println("Quando arriva " + Thread.currentThread().getName() + " il saldo �: " + ContoCorrente.getInstance().getSaldo());
		System.out.println("La somma che vuole prelevare " + Thread.currentThread().getName() + " �: " + sommaDaPrelevare);
		
		try {
			ContoCorrente.getInstance().prelievoNonSync(sommaDaPrelevare);
			System.out.println(Thread.currentThread().getName() + " TUTTO OK PRELIEVO EFFETTUATO");
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + " NOOOOOOOOOOO NON HAI SOLDI!!!");
			e.printStackTrace();
		}
	}
}
