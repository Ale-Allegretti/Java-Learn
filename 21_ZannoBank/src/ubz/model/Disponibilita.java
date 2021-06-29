package ubz.model;

import java.util.Map;

public class Disponibilita extends Soldi {

	private static final long serialVersionUID = 1L;

	public Disponibilita(Map<Taglio,Integer> mappaDisponibilita) {
		super(mappaDisponibilita);
	}
	
	public void aggiorna(Taglio t, int value){ 
		super.setQuantita(t, value);
	}


}
