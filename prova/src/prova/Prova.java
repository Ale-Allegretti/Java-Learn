package prova;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.StringTokenizer;

public class Prova {
	public static void main(String args[]) throws ParseException {
		NumberFormat euroFormatter = NumberFormat.getCurrencyInstance(Locale.FRENCH);

		String stringa = "30,00 \u20AC;";
		

		StringTokenizer st = new StringTokenizer(stringa, ";");
		String priceString = st.nextToken().trim();

		ParsePosition position = new ParsePosition(0);

		System.out.println(priceString);

		System.out.println(euroFormatter.parse(priceString, position));

		// Stream.of(items).forEach(s -> System.out.print(s));
		System.out.print("\n");

		
		//String[] items = stringa.split("[-]+");
		/*
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.ITALY);
		// points
		double points = 1.78;

		String formatoString = n.format(points).toString() + ";";
		System.out.println(formatoString);
		StringTokenizer st2 = new StringTokenizer(formatoString, "€;");

		String es2 = st2.nextToken().strip().replace("€", "");
		System.out.println(es2);
		System.out.println(euroFormatter.parse(es2, position).doubleValue()); */

	}

}
