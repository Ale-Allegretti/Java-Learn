package entità;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LaPersona implements Persona, Comparable<LaPersona>{

	private String cognome, nome, luogoDiNascita;
	private LocalDateTime dataDiNascita;
	
	public LaPersona(String cognome, String nome, String luogoDiNascita, LocalDateTime dataDiNascita) {
		this.cognome = cognome;
		this.nome = nome;
		this.luogoDiNascita = luogoDiNascita;
		this.dataDiNascita = dataDiNascita;
	}

	@Override
	public String cognome() {
		return cognome; 
	}

	@Override
	public String nome() {
		return nome;
	}

	@Override
	public LocalDateTime dataDiNascita() {
		return dataDiNascita;
	}

	@Override
	public String luogoDiNascita() {
		return luogoDiNascita;
	}
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

	@Override
	public String toString() {
		return cognome + " " + nome + " nato a " + luogoDiNascita
				+ " il " + dataDiNascita().format(formatter);
	}

	public int compareTo(LaPersona that) {
		if(this.dataDiNascita.isBefore(that.dataDiNascita))
			return -1;
		if(this.dataDiNascita.isAfter(that.dataDiNascita))
			return 1;
		return 0;
	}

}
