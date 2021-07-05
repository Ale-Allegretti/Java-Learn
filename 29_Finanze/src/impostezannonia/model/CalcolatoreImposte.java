package impostezannonia.model;

import java.util.Arrays;

public class CalcolatoreImposte {

	private Fascia[] fasce;
	private final static double AliquotaSpeseMediche = 1/3D;
	
	public CalcolatoreImposte(Fascia[] scaglioni) {
		this.fasce = Arrays.copyOf(scaglioni, scaglioni.length);
	}
	
	public Fascia[] getFasce() {
		return this.fasce;
	}

	public Imposte calcolaImposte(double reddito, double speseMediche, TipologiaContribuente tipologiaContribuente) {
		double redditoImponibile = Math.max(0, 
				reddito - tipologiaContribuente.getNoTaxArea());
		
		if (redditoImponibile == 0)
			return new Imposte(0, 0, 0);
		
		int fasciaMax = determinaIndiceFasciaMax(redditoImponibile);
		
		double imponibileCorrente = redditoImponibile;
		double impostaLorda = 0;
		for(int i = fasciaMax; i >= 0; i--){
			Fascia fasciaCorrente = fasce[i];
			impostaLorda += (imponibileCorrente-fasciaCorrente.getMin())*fasciaCorrente.getAliquota();
			imponibileCorrente = fasciaCorrente.getMin();
		}
		
		double impostaNetta = impostaLorda - speseMediche * AliquotaSpeseMediche;
		if (impostaNetta < 0)
			impostaNetta = 0;
		
		return new Imposte(redditoImponibile, impostaLorda, impostaNetta);
	}

	private int determinaIndiceFasciaMax(double imponibile){
		int i = 0;
		while(i < fasce.length && 
				fasce[i].getMin() < imponibile) i++;
		return i-1;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fasce: \n");
		builder.append(Arrays.toString(fasce));
		return builder.toString();
	}
	
	
}
