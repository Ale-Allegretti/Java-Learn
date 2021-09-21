package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EsempioPatternMatcher {

	public static void main(String[] args) {
//		Pattern p = Pattern.compile("\\d");
//		
//		/*--------------*/
//		String elenco = "1. Juventus, 2. Roma, 3. Napoli, 4. Atalanta, 5. Lazio";
//
//		String[] elencoArray = p.split(elenco);
////
////		for (int i = 1; i < elencoArray.length; i++) {
////			System.out.println(i + elencoArray[i].replaceAll(",", ""));
////		}
//
//		/*--------------*/
//		String testo = "info@paolopreite.it";
////
////		boolean match = Pattern.matches(".*@.*", testo);
////		System.out.println("il testo contiene la @? " + match);
////
////		/*--------------*/
////		System.out.println(p.pattern());
////
////		/*--------------*/
//		p = Pattern.compile("PAOLO", Pattern.CASE_INSENSITIVE);
//
//		elencoArray = p.split(testo);
//
//		for (int i = 1; i <= elencoArray.length; i++) {
//			System.out.println(i + " " + elencoArray[i-1]);
//		}
////
////		/*--------------*/
//		System.out.println(p.flags());

		
		
		
		
		 
//		/************ MATCHER ******************/
		
//		System.out.println("/************ MATCHER ******************/");
//
//		String text = 
//				"<h1>Titolo 1</h1>"+
//				"<p>Testo A</p>"+
//				"<hr/>" + 		
//				"<p>Testo B</p>"+
//				"<h2>Titolo 2</h2>" + 
//				"<p>Testo C</p>";
//
//		/*
//		 * gruppo 0 - <p>([^<]+)</p>
//		 * gruppo 1 - <p>([^<]+)</p>
//		 * gruppo 2 - [^<]+
//		 */
//		String regex = "(<p>([^<]+)</p>)";
//		
//		Pattern pattern = Pattern.compile(regex);
//		
//		Matcher matcher = pattern.matcher(text);
//
////		System.out.println("gruppi presenti nell'espressione regolare " + regex + ": " + matcher.groupCount());
//
//		System.out.println(text);
//		
//		while(matcher.find()) {
//			System.out.println(matcher.group());
//			System.out.println(matcher.group(1));
//			System.out.println(matcher.group(2));
//			System.out.println("----------");
//		}
//
//		System.out.println("SECONDA ITERAZIONE");
//		while(matcher.find()) {
//			System.out.println(matcher.group());
//
//			System.out.println(matcher.group(1));
//			System.out.println(matcher.group(2));
//		}
//
//		matcher.reset();
//
//		System.out.println("TERZA ITERAZIONE");
//		while(matcher.find()) {
//			System.out.println(matcher.group());
//
//			System.out.println(matcher.group(1));
//			System.out.println(matcher.group(2));
//		}
//
//		matcher.reset("<h1>Lorem</h1><p>Lorem ipsum</p><p>dolor sit amet</p><p>consectetur adipiscing elit</p><p>sed do eiusmod tempor</p>");
//
//		System.out.println("QUARTA ITERAZIONE");
//		while(matcher.find()) {
//			System.out.println(matcher.group());
//
//			System.out.println(matcher.group(1));
//			System.out.println(matcher.group(2));
//		}
//
//
//		System.out.println(matcher.pattern().pattern());
//
//		/* cancello tutti i paragrafi dal testo */
//		String newText = matcher.replaceAll("");
//		System.out.println(newText);
//
//		/* cancello solo il primo paragrafo dal testo */
//		newText = matcher.replaceFirst("");
//		System.out.println(newText);
//
//		regex = "<p>[^<]+</p>";
//		Pattern pattern2 = Pattern.compile(regex);
//		Matcher matcher2 = pattern2.matcher(text);
//
//		System.out.println("gruppi presenti nell'espressione regolare " + regex + ": " + matcher2.groupCount());
//		
		
		
		
		
		
		
		
		EsempioPatternMatcher ex = new EsempioPatternMatcher();
//		System.out.println(ex.emailValidator("info@paolopreite.it"));
//		System.out.println(ex.emailValidator("paolo.preite@paolopreite.it"));
//		System.out.println(ex.emailValidator("paolo_preite@paolopreite.it"));
//		System.out.println(ex.emailValidator("paolo-preite@paolopreite.it"));
//		System.out.println(ex.emailValidator("paolo-preite@paolopreite.com"));
//		System.out.println(ex.emailValidator("paolo-preite@paolopreite.co.uk"));
//		System.out.println(ex.emailValidator("paolo-preite@paolopreite.name"));
//		System.out.println(ex.emailValidator("paolo%preite@paolopreite.it"));
//		System.out.println(ex.emailValidator("Prova"));
//		System.out.println(ex.emailValidator("info@test.comoi"));
//		System.out.println(ex.emailValidator("@paolopreite.it"));
//		
//		System.out.println(ex.dateFormatValidator("10/12/2016"));
//		System.out.println(ex.dateFormatValidator("10-12-2016"));
//		System.out.println(ex.dateFormatValidator("31-12-2016"));
//		System.out.println(ex.dateFormatValidator("31-12-1899"));
//		System.out.println(ex.dateFormatValidator("10\\12\\2016"));
//		System.out.println(ex.dateFormatValidator("2016/02/14"));
//		System.out.println(ex.dateFormatValidator("12/2016"));
//		System.out.println(ex.dateFormatValidator("dd/mm/yyyy"));
//		
		
		/* CFRRMO78P28H501Z */
		
		System.out.println(ex.codiceFiscaleValidator("PRTPPP77P15H501K"));
		System.out.println(ex.codiceFiscaleValidator("PRTPPP77P15H501KPOOI"));
		System.out.println(ex.codiceFiscaleValidator("PRTPPP77P15H"));
		System.out.println(ex.codiceFiscaleValidator("123PPP77P15H501K"));
		System.out.println(ex.codiceFiscaleValidator("PRT12377P15H501K"));
		System.out.println(ex.codiceFiscaleValidator("PRTPPPAAP15H501K"));
	
//		System.out.println(ex.passwordValidator("12345678Mp"));
//		System.out.println(ex.passwordValidator("12345Mp"));
//		System.out.println(ex.passwordValidator("12345678"));
//		System.out.println(ex.passwordValidator("12345678MM"));
//		System.out.println(ex.passwordValidator("12345678pk"));
	}

	public boolean emailValidator(String email) {
		String regex = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);

		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Data in formato gg/mm/aaaa
	 * 
	 * @param date
	 * @return
	 */
	public boolean dateFormatValidator(String date) {
		String regex = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[-/.](19|20)\\d\\d";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(date);

		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	public boolean codiceFiscaleValidator(String cf) {
		String regex = "[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]";
		
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(cf);

		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Password di lunghezza tra 8 e 20 caratteri, che deve contenere un numero, un carattere minuscolo, un carattere maiuscolo.
	 * 
	 * @param pw
	 * @return
	 */
	public boolean passwordValidator(String pw) {
		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(pw);
		
		if(matcher.matches()) {
			return true;
		}
		
		return false;
	}
}
