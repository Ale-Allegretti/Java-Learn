package persona;

public class MainPersona {
	
	private static String getAsString(Persona p) {
		String str = "";
		String name = p.getNameSurname();
		int birth = p.getYearOfBirth();

		str += name + " nato/a nel " + birth;
		
		return str;
	}
	
	public static void main(String[] args) {
		
		Persona pers1 = new Persona("Mario Rossi", 1980);	//anno come int
		System.out.println("Creata la persona 1: " + getAsString(pers1));
	
		Persona pers2 = new Persona("Pino Bianchi", "1977");	//anno come string
		System.out.println("Creata la persona 2: " + getAsString(pers2));
		
		Persona pers3 = new Persona("mario RoSsi", 1999);	
		System.out.println("Creata la persona 3: " + getAsString(pers3) + "\n");
		
		//omonimia
		
		if (pers1.omonimo(pers2))
			System.out.println(getAsString(pers1) + " e " + getAsString(pers2) + " sono omononimi");
		else
			System.out.println(getAsString(pers1) + " e " + getAsString(pers2) + " non sono omononimi");
		
		if (pers1.omonimo(pers3))
			System.out.println(getAsString(pers1) + " e " + getAsString(pers3) + " sono omononimi" + "\n");
		else
			System.out.println(getAsString(pers1) + " e " + getAsString(pers3) + " non sono omononimi" + "\n");
		
		
		//età
		
		if (pers1.olderThan(pers2) == -1)
			System.out.println(getAsString(pers1) + " è più grande di " + getAsString(pers2));
		else if (pers1.olderThan(pers2) == 0)
			System.out.println(getAsString(pers1) + " e " + getAsString(pers2) + " sono coetanei");
		else
			System.out.println(getAsString(pers2) + " è più grande di " + getAsString(pers3));
		
		
	}	
}
