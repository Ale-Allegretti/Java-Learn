package ubz.model;

import ubz.persistence.DotazioneLoader;

public class CassiereUbz extends Cassiere {

	private Politiche politiche;

	public CassiereUbz(DotazioneLoader loader) {
		super(loader);

		this.politiche = loader.getPolitiche();
	}

	@Override
	protected int calcolaQuantitaDiQuestoTaglio(Taglio t, int importo){
		 int quantitaDiQuestoTaglio = super.calcolaQuantitaDiQuestoTaglio(t, importo);
		 int maxErogabileSecondoPolitica = politiche.getQuantita(t);
		 quantitaDiQuestoTaglio = Math.min(maxErogabileSecondoPolitica, quantitaDiQuestoTaglio);
		 return quantitaDiQuestoTaglio;
	}

}
