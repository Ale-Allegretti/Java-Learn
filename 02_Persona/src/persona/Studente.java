package persona;

public class Studente extends Persona {
	private long matricola;
	
	public Studente(String nameSurname, int yearOfBirth, long matricola) {
		super(nameSurname, yearOfBirth);
		this.matricola = matricola;
	}
	
	public void show() {
		super.show();
		System.out.print(" " + matricola);
	}
}
