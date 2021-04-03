

public class MainEnum {
	public static void main (String [] args) {
	Portafoglio pf1 = new Portafoglio(10);
	
	for(Taglio t: new Taglio[] { 
			Taglio.CINQUANTA, Taglio.VENTI , Taglio.DIECI , Taglio.DUE ,
			Taglio.DUE , Taglio.UNO }) 
		pf1.add(t);
	
	Portafoglio pf2 = new Portafoglio(new Taglio[] {
	Taglio.CINQUANTA , Taglio.CINQUANTA , Taglio.VENTI ,
	Taglio.DIECI , Taglio.DUE , Taglio.DUE , Taglio.DUE , Taglio.UNO});
	
	System.out.println ("Contenuto portafoglio: " + pf1);
	System.out.println ("Valore portafoglio: " + pf1.getValore());
	System.out.println ("Contenuto portafoglio: " + pf2);
	System.out.println ("Valore portafoglio: " + pf2.getValore());
	
	}
}
