package oroscopo.model;

public class OroscopoMensile implements Oroscopo, Comparable<Oroscopo> {
	
	private Previsione amore;
	private Previsione lavoro;
	private Previsione salute;
	private SegnoZodiacale segnoZodiacale;
	

	public OroscopoMensile(SegnoZodiacale segno, Previsione amore, Previsione lavoro, Previsione salute) {
		if (segno == null || amore == null || lavoro == null || salute == null)
			throw new IllegalArgumentException("Valori passati non validi");
		if (!amore.validaPerSegno(segno) || !lavoro.validaPerSegno(segno) || !salute.validaPerSegno(segno))
			throw new IllegalArgumentException("Previsioni passate non compatibili col segno");
		this.segnoZodiacale = segno;
		this.amore = amore;
		this.lavoro = lavoro;
		this.salute = salute;
	}
	
	public OroscopoMensile(String nomeSegnoZodiacale, Previsione amore, Previsione lavoro, Previsione salute) {
		if (SegnoZodiacale.valueOf(nomeSegnoZodiacale) == null || nomeSegnoZodiacale.isBlank())
			throw new IllegalArgumentException("Nome segno passato non valido");
		this.segnoZodiacale = SegnoZodiacale.valueOf(nomeSegnoZodiacale);
		this.amore = amore;
		this.lavoro = lavoro;
		this.salute = salute;
	}
	

	@Override
	public SegnoZodiacale getSegnoZodiacale() {
		return segnoZodiacale;
	}

	@Override
	public Previsione getPrevisioneAmore() {
		return amore;
	}

	@Override
	public Previsione getPrevisioneSalute() {
		return salute;
	}

	@Override
	public Previsione getPrevisioneLavoro() {
		return lavoro;
	}

	@Override
	public int getFortuna() {
		long val = Math.round((amore.getValore() + lavoro.getValore() + salute.getValore()) / 3.0);
		return (int) val;
	}

	
	@Override
	public int compareTo(Oroscopo that) {
		if (that == null)
			throw new IllegalArgumentException("Valore non valido");
		int z1 = this.getSegnoZodiacale().ordinal();
		int z2 = that.getSegnoZodiacale().ordinal();
		
		return z1 < z2 ? -1 : (z1 == z2 ? 0 : 1);
	}

	@Override
	public String toString() {
		return "Amore:\t" + amore.getPrevisione() + "\n" +"Lavoro:\t" + lavoro.getPrevisione() + "\n"
				+ "Salute:\t" + salute.getPrevisione();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amore == null) ? 0 : amore.hashCode());
		result = prime * result + ((lavoro == null) ? 0 : lavoro.hashCode());
		result = prime * result + ((salute == null) ? 0 : salute.hashCode());
		result = prime * result + ((segnoZodiacale == null) ? 0 : segnoZodiacale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OroscopoMensile))
			return false;
		OroscopoMensile other = (OroscopoMensile) obj;
		if (amore == null) {
			if (other.amore != null)
				return false;
		} else if (!amore.equals(other.amore))
			return false;
		if (lavoro == null) {
			if (other.lavoro != null)
				return false;
		} else if (!lavoro.equals(other.lavoro))
			return false;
		if (salute == null) {
			if (other.salute != null)
				return false;
		} else if (!salute.equals(other.salute))
			return false;
		if (segnoZodiacale != other.segnoZodiacale)
			return false;
		return true;
	}

}
