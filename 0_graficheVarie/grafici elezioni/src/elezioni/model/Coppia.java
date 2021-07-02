package elezioni.model;

import java.text.NumberFormat;
import java.text.ParseException;

public class Coppia implements Comparable<Coppia>{
	private String partito;
	long voti;
	
	public Coppia(String partito, long voti) {
		this.partito = partito;
		this.voti = voti;
	}

	public Coppia(String partito, String votiAsFormattedString) throws ParseException {
		this.partito = partito;
		NumberFormat formatter = NumberFormat.getInstance();
		this.voti = formatter.parse(votiAsFormattedString).longValue();
	}

	public String getPartito() {
		return partito;
	}
	
	public long getVoti() {
		return voti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partito == null) ? 0 : partito.hashCode());
		result = prime * result + (int) (voti ^ (voti >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Coppia other = (Coppia) obj;
		if (partito == null) {
			if (other.partito != null)
				return false;
		} else if (!partito.equals(other.partito))
			return false;
		if (voti != other.voti)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Coppia [partito=" + partito + ", voti=" + voti + "]";
	}
	@Override
	public int compareTo(Coppia that) {
		return this.getPartito().compareTo(that.getPartito());
	}	
}
