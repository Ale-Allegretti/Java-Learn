package entità;

import java.time.LocalDateTime;

public class LoStudente extends LaPersona implements Studente {
	private int matricola;

	public LoStudente(String cognome, String nome, String luogoDiNascita, LocalDateTime dataDiNascita, int matricola) {
		super(cognome, nome, luogoDiNascita, dataDiNascita);
		this.matricola = matricola;
	}

	@Override
	public int matricola() {
		return matricola;
	}

	@Override
	public String toString() {
		return super.toString() + " con matricola n.ro " + matricola;
	}

}
