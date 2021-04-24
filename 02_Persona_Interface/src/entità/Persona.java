package entità;

import java.time.LocalDateTime;

public interface Persona {
	public String cognome();
	public String nome();
	public LocalDateTime dataDiNascita();
	public String luogoDiNascita();
}
