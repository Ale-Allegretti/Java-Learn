package oroscopo.controller;

import java.io.IOException;

import oroscopo.model.Oroscopo;
import oroscopo.model.OroscopoMensile;
import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;
import oroscopo.persistence.OroscopoRepository;

public class MyController extends AbstractController {

	private StrategiaSelezione strategiaSelezione;
	
	public MyController(OroscopoRepository myReader, StrategiaSelezione strategiaSelezione) throws IOException {
		super(myReader);
		this.strategiaSelezione = strategiaSelezione;
	}

	public SegnoZodiacale[] getSegni() {
		return SegnoZodiacale.values();
	}
	
	public Oroscopo[] generaOroscopoAnnuale(SegnoZodiacale segno, int fortunaMin) {
		Oroscopo[] res = new Oroscopo[12];
		int fortunaMedia = 0;
		do {
			for (int i = 0; i < res.length; i++) {
				res[i] = generaOroscopoCasuale(segno);
				fortunaMedia += res[i].getFortuna() / (i + 1);
			}
		} while (fortunaMin > fortunaMedia);
		
		return res;
	}

	public Oroscopo generaOroscopoCasuale(SegnoZodiacale segno) {
		Previsione amore = this.strategiaSelezione.seleziona(getRepository().getPrevisioni("AMORE"), segno);
		Previsione lavoro =  this.strategiaSelezione.seleziona(getRepository().getPrevisioni("LAVORO"), segno);
		Previsione salute =  this.strategiaSelezione.seleziona(getRepository().getPrevisioni("SALUTE"), segno);
		
		return new OroscopoMensile(segno, amore, lavoro, salute);
	}
}
