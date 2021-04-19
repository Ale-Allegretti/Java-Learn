package persona2;

public class MainPersona2 {
	
	public static void main(String[] args) {
		
		var pers1 = new Persona2("Mario Rossi", 1980);	
		System.out.println(pers1);		// toString generata automaticamente
		
		var pers2 = new Persona2("Giovanni Rossi", 1994);
		
		System.out.println(pers1 == pers2);			// equals e hashcode automaticamente generati correttamente
		System.out.println(pers1.hashCode() == pers2.hashCode());
		
		System.out.println(pers2.nome());	// accessors generati automaticamente
		System.out.println(pers2.anni());
	}	
}