package unident.model;

import java.text.NumberFormat;
import java.util.*;



public class Carriera {
	
	private SortedMap<AttivitaFormativa, List<Esame>> esami;
	private NumberFormat numberFormat;
	
	public Carriera(Set<AttivitaFormativa> pianoDidattico) {
		this.esami = new TreeMap<>(Comparator.comparing(AttivitaFormativa::getNome));
		for (AttivitaFormativa act : pianoDidattico) 
			this.esami.put(act, new ArrayList<>());
		this.numberFormat = NumberFormat.getNumberInstance();
		this.numberFormat.setMaximumFractionDigits(2);
		this.numberFormat.setMinimumFractionDigits(2);
	}
	
	public void registra(Esame esame) {
		AttivitaFormativa af = esame.getAf();
		List<Esame> listaTentativi = esami.get(af);
		if (listaTentativi == null) {
			throw new IllegalArgumentException("Attività formativa non presente in carriera");
		}
		if (!listaTentativi.isEmpty()) {
			Esame ultimoEsame = listaTentativi.get(listaTentativi.size()-1);
			if (ultimoEsame.getVoto().getValue().isPresent()) {
				throw new IllegalArgumentException("Esame già superato con esito positivo");
			}
			if(ultimoEsame.getDate().isEqual(esame.getDate())) {
				throw new IllegalArgumentException("Ultimo esame registrato ha data identica all'attuale");
			}
			if(ultimoEsame.getDate().isAfter(esame.getDate())) {
				throw new IllegalArgumentException("Ultimo esame registrato ha data successiva all'attuale");
			}
		}
		listaTentativi.add(esame);
	}
	
	public List<Esame> istanzeDi(AttivitaFormativa af) {
		return this.esami.get(af);
	}
	
	
	public double mediaPesata() {
		double resNum = 0.0;
		double resDen = 0.0;
		for (AttivitaFormativa af : this.esami.keySet()) {
			if (istanzeDi(af) != null) {
				for (Esame esame: istanzeDi(af)) {
					if (esame.getVoto().getValue().isPresent()) {
					resNum += esame.getVoto().getValue().getAsInt()*esame.getAf().getCfu();
					resDen += esame.getAf().getCfu();
					}
				}
			}
		}
		
		return 1.0*resNum/resDen;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Esami sostenuti:");
		builder.append(System.lineSeparator());
		for(AttivitaFormativa af:esami.keySet()) {
			if (istanzeDi(af)!=null) {
				for (Esame esame: istanzeDi(af)) {
					builder.append(esame); 
					builder.append(System.lineSeparator());
				}
			}
		}
		builder.append(System.lineSeparator());
		builder.append("Media pesata: " + numberFormat.format(this.mediaPesata()) + "/30");
		builder.append(System.lineSeparator());
		return builder.toString();
	}
	
}
