package zannotaxi.model;

import java.util.Optional;

public class TariffaADistanza implements ITariffaTaxi {
	
	private double costoMassimo;
	private double costoMinimo;
	private double distanzaDiScatto;
	private String nome;
	private double valoreScatto;
	private double velocitaMassima;
	private double velocitaMinima;
	
	
	public TariffaADistanza(String nome, double velocitaMinima, double velocitaMassima, double costoMinimo,
							double costoMassimo, double valoreScatto, double distanzaDiScatto) {

		this.costoMassimo = costoMassimo;
		this.costoMinimo = costoMinimo;
		this.distanzaDiScatto = distanzaDiScatto;
		this.nome = nome;
		this.valoreScatto = valoreScatto;
		this.velocitaMassima = velocitaMassima;
		this.velocitaMinima = velocitaMinima;
	}
	
	@Override
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public double getValoreScatto() {
		return this.valoreScatto;
	}

	
	@Override
	public Optional<Scatto> getScattoCorrente(int tempoTrascorsoDaUltimoScatto, double spazioPercorsoDaUltimoScatto,
			double costoCorrente) {
		double velocitaMedia = (spazioPercorsoDaUltimoScatto / tempoTrascorsoDaUltimoScatto) * 3.6;  // misurata già in Km/h
		boolean effettuaScatto = false;
		if (velocitaMedia >= this.getVelocitaMinima() &&
			velocitaMedia < this.getVelocitaMassima() &&
			costoCorrente >= this.getCostoMinimo() &&
			costoCorrente < this.getCostoMassimo() &&
			Math.round(spazioPercorsoDaUltimoScatto) >= this.getDistanzaDiScatto())
				effettuaScatto = true;
		
		return effettuaScatto ? 
				Optional.of(new Scatto(tempoTrascorsoDaUltimoScatto, this.getDistanzaDiScatto(), this.getValoreScatto())) 
				: Optional.empty();
	}

	public double getCostoMassimo() {
		return costoMassimo;
	}

	public double getCostoMinimo() {
		return costoMinimo;
	}

	public double getDistanzaDiScatto() {
		return distanzaDiScatto;
	}

	public double getVelocitaMassima() {
		return velocitaMassima;
	}

	public double getVelocitaMinima() {
		return velocitaMinima;
	}

	
	
	

}
