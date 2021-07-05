package ghigliottina.model;

import java.util.Iterator;
import java.util.List;

public class Ghigliottina implements Iterable<Terna> {

	private Iterator<Terna> iterator;
	private String rispostaEsatta;
	private List<Terna> terne;
	
	
	
	public Ghigliottina(List<Terna> terne, String rispostaEsatta) {
		if (terne == null)
			throw new IllegalArgumentException("Lista terne vuota o nulla");
		if (rispostaEsatta == null || rispostaEsatta.isBlank())
			throw new IllegalArgumentException("Risposta vuota o nulla");
		this.rispostaEsatta = rispostaEsatta;
		this.terne = terne;
		this.iterator = this.terne.listIterator();
	}
	
	
	@Override
	public Iterator<Terna> iterator() {
		return this.iterator;
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public Terna next() {
		if (!this.hasNext())
			return null;
		return this.iterator().next();
	}
	

	public String getRispostaEsatta() {
		return rispostaEsatta;
	}


	public List<Terna> getTerne() {
		return terne;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ghigliottina [rispostaEsatta=");
		builder.append(rispostaEsatta);
		builder.append(", terne=");
		builder.append(terne);
		builder.append("]");
		return builder.toString();
	}

	
}
