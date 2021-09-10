package classiinnerlocali;

import java.util.List;

import classiinnerlocali.Appartamento.StanzaInner;

public class Main {

	public static void main(String[] args) {
		Appartamento a = new Appartamento();
		
		
		a.setPiano(0);
		
		a.getStanzeSt().add(new StanzaStandard(16, "angolo cottura"));
		
//		a.getStanzeIn().add(new StanzaInner(16, "angolo cottura"));
		
		a.aggiungiStanza(30, "angolo cottura");
		
		
		List<StanzaStandard> st = a.getStanzeSt();
		
		for (StanzaStandard stanzaStandard : st) {
			System.out.println(stanzaStandard.getMq());
		}
		
		
		List<StanzaInner> st2 = a.getStanzeIn();
		
		for (StanzaInner stanzaInner : st2) {
			System.out.println(stanzaInner.getMq());
		}
	}

}
