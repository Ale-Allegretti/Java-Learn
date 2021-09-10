package proveVarie;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.StringTokenizer;

public class Prova {
	public static void main(String args[]) throws ParseException {
		NumberFormat euroFormatter = NumberFormat.getNumberInstance(Locale.ITALY);

		String stringa = "0--15.123: 5%\n";

		StringTokenizer tokenizer = new StringTokenizer(stringa, "-:\t");
		String min = tokenizer.nextToken().trim();
		String max = tokenizer.nextToken().trim(); 
		String perc = tokenizer.nextToken().trim();

		System.out.println(min);
		System.out.println(max);
		System.out.println(perc);

		System.out.println(euroFormatter.parse(perc));

		
		
		

	}

}
