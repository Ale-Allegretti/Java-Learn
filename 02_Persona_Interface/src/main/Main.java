package main;

import java.time.LocalDateTime;
import java.util.Arrays;

import entità.*;

public class Main {

	public static void main(String[] args) {
		Persona[] persone = {
			new LaPersona("Rossi", "Mario", "Bologna", LocalDateTime.of(1998,12,25,13,20)),
			new IlLavoratore ("Neri", "Giacomo", "Bologna", LocalDateTime.of(1985, 2, 15, 11, 50), "lo chef", 5000),
			new LoStudente ("Verdi", "Paolo", "Bologna", LocalDateTime.of(2001,3,27,14,14), 92453),
			new LoStudenteLavoratore ("Bruni", "Elvio", "Bologna", LocalDateTime.of (1994, 4, 25, 4, 51), 98784, "commesso", 1100) };
		
		for (Persona p : persone)
			System.out.println(p);	
		
		Arrays.sort(persone);
		
		for (Persona p : persone)
			System.out.println(p);
		
	}

}
