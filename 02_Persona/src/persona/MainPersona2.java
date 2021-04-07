package persona;

public class MainPersona2 {
	
	public static void main(String[] args) {
		
		Persona pers1 = new Persona("Mario Rossi", 1980);	//anno come int
		System.out.print("Creata la persona 1: "); pers1.show();
	
		Studente stud1 = new Studente("Gino Rossi", 1990, 9029219);
		System.out.print("\nCreato lo studente 1: "); stud1.show();
		
		
	}	
}