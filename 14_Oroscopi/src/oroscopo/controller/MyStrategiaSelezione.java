package oroscopo.controller;

import java.util.List;
import java.util.Random;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class MyStrategiaSelezione implements StrategiaSelezione {

	private Random r = new Random();
	
	@Override
	public Previsione seleziona(List<Previsione> previsioni, SegnoZodiacale segno) {
		if(previsioni == null || previsioni.isEmpty())
			throw new IllegalArgumentException("Previsioni non valide");
		if(segno == null)
			throw new IllegalArgumentException("Segno non valido");
		
		Previsione res = null;
		
		while(!(res = previsioni.get(r.nextInt(previsioni.size()))).validaPerSegno(segno));
		
		return res;
	}

}
