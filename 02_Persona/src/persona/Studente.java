package persona;

import java.util.Objects;

public class Studente extends Persona {
	private long matricola;
	
	public Studente(String nameSurname, int yearOfBirth, long matricola) {
		super(nameSurname, yearOfBirth);
		this.matricola = matricola;
	}
	
	public <T> boolean idem(T[] a, T[] b) {  		// da inserire qui o anche nel Main stesso
		if (a.length != b.length ) return false;
		for (int i = 0; i < a.length ; i++)
			if (!a[i].equals(b[i])) return false;
		return true;
	}
	
	/*public boolean equals (Object obj) {			// alternativa alla equals con hashcode di cui sotto
		if(obj instanceof Studente)
			return this.matricola == ((Studente) obj).matricola;
		else return false;
	} */
	
	public void show() {	// completa lo show() di Persona
		super.show();
		System.out.println(" " + matricola);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(matricola);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		return matricola == other.matricola;
	}
}


