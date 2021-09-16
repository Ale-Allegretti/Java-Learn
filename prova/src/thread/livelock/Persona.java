 package thread.livelock;

public class Persona {
	private String nome;
	private boolean haSalutato;
	
	public Persona(String nome) {
		setNome(nome);
	}
	
	public void saluta(Persona p) {
		while(!haSalutato) {
			try {
				System.out.println(getNome() + " si mette in attesa che " + p.getNome() + " lo saluti!");
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		haSalutato = true;
		System.out.println("Ciao " + p.getNome());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}