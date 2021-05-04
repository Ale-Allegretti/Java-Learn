package zannotaxi.model;

import java.util.Optional;

public class TariffaATempo implements ITariffaTaxi {
	
	private String nome;
	private int tempoDiScatto;
	private double valoreScatto;
	private double velocitaMassima;
	private double velocitaMinima;
	
	public TariffaATempo(String nome,  double velocitaMinima, double velocitaMassima,
							double valoreScatto,int tempoDiScatto) {
		this.nome = nome;
		this.tempoDiScatto = tempoDiScatto;
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
			tempoTrascorsoDaUltimoScatto >= this.getTempoDiScatto())
			effettuaScatto = true;
	
		return effettuaScatto ? Optional.of(new Scatto(tempoDiScatto, spazioPercorsoDaUltimoScatto, valoreScatto)) 
				: Optional.empty();
	}

	public int getTempoDiScatto() {
		return tempoDiScatto;
	}

	public double getVelocitaMassima() {
		return velocitaMassima;
	}

	public double getVelocitaMinima() {
		return velocitaMinima;
	}
	
}
