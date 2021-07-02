package elezioni.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Risultato {
	private Map<String, List<Coppia>> risultato;

	public Risultato() {
		this.risultato = new HashMap<>();
	}
	
	public void add(String comune, Coppia c) {
		List<Coppia> tmp = risultato.get(comune);
		if (tmp==null) tmp=new ArrayList<>();
		tmp.add(c);
		Collections.sort(tmp);
		risultato.put(comune, tmp);
	}

	public List<Coppia> getRisultato(String comune) {
		return risultato.get(comune);
	}
	
	public int size() {
		return risultato.size();
	}

	public SortedSet<String> getComuni() {
		return new TreeSet<>(risultato.keySet());
	}
	
	@Override
	public String toString() {
		return "Risultato [risultato=" + risultato + "]";
	}
	
}
