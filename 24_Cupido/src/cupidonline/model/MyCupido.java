package cupidonline.model;


import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class MyCupido extends Cupido {

	public MyCupido(Map<String, Persona> iscritti, Map<String, Preferenza> preferenze) {
		super(iscritti, preferenze);
	}

	@Override
	public SortedSet<Corrispondenza> trovaCorrispondenze(String pName, Preferenza pPref) {
		SortedSet<Corrispondenza> res = new TreeSet<>();
		for (Persona persona : this.getIscritti())
			if (persona.getSesso().compareTo(pPref.getSesso()) == 0)
				res.add(new Corrispondenza(pName, persona.getId(), indiceCompatibilità(persona, pPref)));
		
		res.stream().sorted(Comparator.comparing(Corrispondenza::getNomePersona2));
		return res;
	}

	@Override
	public int indiceCompatibilità(Persona q, Preferenza pref) {
		int preferenzaEta = Math.abs(pref.getEtaMax() - q.getEta()) <= Math.abs(pref.getEtaMin() - q.getEta()) ?
				pref.getEtaMax() : pref.getEtaMin();
		
		int eta = preferenzaEta == q.getEta() ? 100 : (100 - (Math.abs(preferenzaEta - q.getEta())));
		int luogoRes = q.getCitta().equals(pref.getCitta()) ? 100 : q.getProvincia().equalsIgnoreCase(pref.getProvincia()) ? 90 :
				q.getRegione().equals(pref.getRegione()) ? 60 : 40;
		System.out.println(luogoRes);
		int segnoZ = 100;
		if (!pref.getSegnoZodiacale().isEmpty())
			segnoZ = q.getSegnoZodiacale().compareTo(pref.getSegnoZodiacale().get()) == 0 ? 100 : 90;
		int fisico = 100;
		int altezza = 100;
		if (pref.getAltezza().isPresent()) {
			altezza = (int) (q.getAltezza() == pref.getAltezza().get() ? 100 : 
				Math.abs(pref.getAltezza().get() - q.getAltezza()))/100;
			if (altezza != 100)
				fisico -= altezza;
		}
		int peso = 100;
		if (pref.getPeso().isPresent()) {
			peso = q.getPeso() == pref.getPeso().getAsInt() ? 100 : Math.abs(pref.getPeso().getAsInt() - q.getPeso());
			if (peso != 100)
				fisico -= peso;
		}
		int adim = 100;
		if (pref.getColoreCapelli().isPresent() && q.getColoreCapelli().compareTo(pref.getColoreCapelli().get()) != 0)
			adim = 95;
		if (pref.getColoreOcchi().isPresent() && q.getColoreCapelli().compareTo(pref.getColoreOcchi().get()) != 0)
			adim = 95;
		
		int a = Math.min(eta, luogoRes);
		int b = Math.min(segnoZ, fisico);
		
		return a < b ? Math.min(a, adim) : Math.min(b, adim);
	}

}
