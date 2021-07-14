package vendoerivendo.model;

public class MyAnnuncio implements Annuncio, Comparable<Annuncio> {

	private Prodotto prodotto;
	private Estetica estetica;
	private double sconto;
	
	public MyAnnuncio(Prodotto prodotto, Estetica estetica, double sconto) {
		if (prodotto == null)
			throw new IllegalArgumentException("Prodotto nullo");
		if (estetica == null  )
			throw new IllegalArgumentException("Estetica nulla");
		if (sconto < 0)
			throw new IllegalArgumentException("Sconto nullo");
		this.prodotto = prodotto;
		this.estetica = estetica;
		this.sconto = sconto;
	}
	
	

	@Override
	public int compareTo(Annuncio that) {
		int r1 = this.prodotto.getCategoriaMerceologica().compareTo(that.getCategoriaMerceologica());
		int r2 = this.getEstetica().compareTo(that.getEstetica());
		int r3 = this.getPrezzo() == that.getPrezzo() ? 0 : this.getPrezzo() > that.getPrezzo() ? 1 : -1;
			
		return r1 != 0 ? r1 : r2 != 0 ? r2 : r3 == 0 ? 0 : r3;
	}

	@Override
	public double getPrezzo() {
		return this.prodotto.getValore() - (this.prodotto.getValore())*this.getSconto();
	}
	
	@Override
	public CategoriaMerceologica getCategoriaMerceologica() {
		return this.prodotto.getCategoriaMerceologica();
	}
	
	@Override
	public Prodotto getProdotto() {
		return prodotto;
	}

	@Override
	public Estetica getEstetica() {
		return estetica;
	}

	@Override
	public double getSconto() {
		return sconto;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Annuncio: \n");
		builder.append(prodotto.toString());
		builder.append(", estetica:");
		builder.append(estetica.toString());
		builder.append(", sconto:");
		builder.append(sconto);
		System.out.println(builder.toString());
		return builder.toString();
	}

	

}
