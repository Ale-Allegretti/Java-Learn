package prova;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class provaIterator {

	public static void main(String args[]) {
		Map<String, Integer> stringa = new HashMap<>();

		stringa.put("XXX", 4);
		stringa.put("YYY", 3);
		stringa.put("FFF", 12);

		Iterator<String> iterator = stringa.keySet().iterator();
		int cont = 0;
		while (iterator.hasNext()) {
			String n = iterator.next();
			cont++;
			System.out.println(cont + ") " + n);
		}

	}

}
