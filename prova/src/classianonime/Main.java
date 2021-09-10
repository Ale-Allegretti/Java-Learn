package classianonime;

public class Main {

	public static void main(String[] args) {
		
		Main m = new Main();
		
		Azione a1 = m.eseguiAzione();
		a1.eseguiAzione();
		
		Azione a2 = m.eseguiAzione2();
		a2.eseguiAzione();
	}

	public Azione eseguiAzione() {
		return new AzioneImpl();
	}
	
	public Azione eseguiAzione2() {
		return new Azione() {
			@Override
			public void eseguiAzione() {
				System.out.println("sono nella classe anonima");
			}
		};
	}
}
