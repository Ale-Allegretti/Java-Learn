package lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		Main m = new Main();

		
//		PREDICATE :
		
		List<Utente> utenti = m.cercaUtenti(m.elencoUtenti(), u -> u.getNome().equals("Paolo"));
		System.out.println("UTENTI CHE SI CHIAMANO PAOLO - JAVA 8");
		for (Utente utente : utenti) {
			System.out.println(utente.getNome() + " " + utente.getCognome() + " " + utente.getEta());
		}
		
		utenti = m.cercaUtenti(m.elencoUtenti(), u -> u.getEta() > 40);
		System.out.println("UTENTI CHE HANNO PIU' DI 40 ANNI - JAVA 8");
		for (Utente utente : utenti) {
			System.out.println(utente.getNome() + " " + utente.getCognome() + " " + utente.getEta());
		}
		
		
		
		Iterator<Utente> elencoUtenti = m.elencoUtenti().iterator();
		utenti = new ArrayList<Utente>();
		while(elencoUtenti.hasNext()) {
			Utente u = elencoUtenti.next();

			if(u.getNome().equals("Paolo")) {
				utenti.add(u);
			}
		}
		System.out.println("UTENTI CHE SI CHIAMANO PAOLO - PRIMA DI JAVA 8");
		for (Utente utente : utenti) {
			System.out.println(utente.getNome() + " " + utente.getCognome() + " " + utente.getEta());
		}
	
		
		

//		CONSUMER :
		
//		System.out.println("STAMPA ELENCO UTENTI - CON JAVA 8");
//
//		List<Utente> utenti = m.elencoUtenti();
//
//		Consumer<Utente> consumer = s -> System.out.println("Utente: " + s.getCognome() + " " + s.getNome());
//		utenti.forEach(consumer);
//
//		/* Al posto del consumer, prima di Java 8... */
//		System.out.println("STAMPA ELENCO UTENTI - PRIMA DI JAVA 8");
//
//		utenti = m.elencoUtenti();
//		Iterator<Utente> elencoUtenti = utenti.iterator();
//
//		while(elencoUtenti.hasNext()) {
//			Utente u = elencoUtenti.next();
//
//			System.out.println("Utente: " + u.getCognome() + " " + u.getNome());
//		}
//
//		/* altro esempio di Consumer... */
//		System.out.println("STAMPA ELENCO UTENTI - ALTRO ESEMPIO CON JAVA 8");
//		Consumer<Utente> stampaUtente = u -> System.out.println(u.getCognome() + " " + u.getNome());
//
//		for (Utente utente : utenti) {
//			stampaUtente.accept(utente);
//		}

		
		
		
//		SUPPLIER :
		
//		System.out.println("STAMPA ELENCO UTENTI - ESEMPIO CON SUPPLIER JAVA 8");
//		utenti = m.elencoUtenti();
//
//		for (Utente utente : utenti) {
//			m.stampaDatiUtente(() -> utente);
//		}
//
//		/* Stessa cosa con Consumer e Supplier insieme... */
//		System.out.println("STAMPA ELENCO UTENTI - ESEMPIO CON CONSUMER E SUPPLIER INSIEME JAVA 8");
//		utenti = m.elencoUtenti();
//		utenti.forEach(
//				s -> {
//					m.stampaDatiUtente(() -> s);
//				});
//		
//		/* Esempio Function Java 8 */
//		System.out.println("CALCOLA NUMERO CARATTERI NOME+COGNOME - ESEMPIO FUNCTION JAVA 8");
//		
//		/* calcolo il quadrato */
//		double a = 12;
//		m.calcolaOperazione(a, val-> val * val);
//		
//		/* calcolo la radice quadrata */
//		m.calcolaOperazione(a, val-> Math.sqrt(a));
//		
//		utenti = m.elencoUtenti();
//		
//		for (Utente utente : utenti) {
//			String out1 = m.stampaDatiUtente(utente, s -> s.getCittaResidenza() + " " + s.getEmail());
//			String out2 = m.stampaDatiUtente(utente, s -> s.getNome() + " " + s.getCognome());
//
//			System.out.println(out1);
//			System.out.println(out2);
//		}
		
		
		
		
		
		/* UnaryOperator */
//		UnaryOperator<String> unop = str -> str.toLowerCase();
//		UnaryOperator<Long> unop2 = val -> val*val;
//
//		System.out.println(unop.apply("Prova Di Stampa Minuscolo"));
//
//		long num = 10;
//		System.out.println("Il quadrato di " + num + " � " + unop2.apply(num));
		
		
		
				
		/* BinaryOperator */
//		BinaryOperator<Double> biop = (a1, a2) -> a1 * a2;
//		
//		double x = 10.5;
//		double y = 15;
//		
//		System.out.println("La moltiplicazione tra " + x + " ed " + y + " �: " + biop.apply(x, y));
//		
//		BinaryOperator<String> biop2 = (s1, s2) -> "Ciao " + s1 + " " + s2;
//		
//		List<Utente> utenti = m.elencoUtenti();
//		
//		for (Utente utente : utenti) {
//			System.out.println(biop2.apply(utente.getNome(), utente.getCognome()));
//		}
		
	} // FINE MAIN
	
	
	
	
	/* Esempio Supplier*/
	private void stampaDatiUtente(Supplier<Utente> u) {
		System.out.println(u.get().getCognome() + " " + u.get().getNome() + " " + u.get().getEmail());
	}

	/* Esempio Function */
	private void calcolaOperazione(double operando, Function<Double, Double> funzione){	
		double risultato = funzione.apply(operando);
		
		System.out.println("Il risultato dell'operazione è " + risultato);
	}

	/* Esempio Function */
	public  String stampaDatiUtente(Utente u, Function<Utente, String> utente){
        return utente.apply(u);
    }
	
	
	
	private List<Utente> elencoUtenti() {
		List<Utente> utenti = new ArrayList<Utente>();

		utenti.add(new Utente("Paolo", "Preite", 39, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Mario", "Rossi", 40, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Antonio", "Di Girolamo", 23, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Caterina", "Montefalco", 55, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Valeria", "Natelli", 45, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Giovanna", "D'Antonelli", 50, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Paolo", "Pisani", 21, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Laura", "Gambaro", 19, "Roma", "info@paolopreite.it", "test"));
		utenti.add(new Utente("Benedetto", "Satini", 38, "Roma", "info@paolopreite.it", "test"));

		return utenti;
	}
	
	
	/*
	 * Predicate<Utente> permette di usare la funzione passata nel ciclo for tramite test 
	 * così da usare criteri diversi di ricerca (in questo caso - vedi riga 21)
	 * 
	 *  */
	public List<Utente> cercaUtenti(List<Utente> utenti, Predicate<Utente> p){
		List<Utente> utentiTrovati = new ArrayList<Utente>();

		for (Utente u:utenti) {
			if (p.test(u)) {
				utentiTrovati.add(u);
			}
		}

		return utentiTrovati;
	}
	
	
}
