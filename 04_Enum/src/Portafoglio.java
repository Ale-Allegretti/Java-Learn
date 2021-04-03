import java.util.Arrays;
import java.util.StringJoiner;


public class Portafoglio {
	private Taglio[] contenuto;
	int logicalSize;
	
	public Portafoglio(int n) {
		contenuto = new Taglio[n];
		logicalSize = 0;
	}
	
	public Portafoglio(Taglio[] contenuto){
		this.contenuto = Arrays.copyOf(contenuto, contenuto.length);
		logicalSize = contenuto.length;
	}
	
	
	public void add(Taglio t) {
		contenuto[logicalSize++] = t;
	}

	public int getValore() {
		int sum = 0;
		for (int i = 0; i < logicalSize ; i++) 
			sum += contenuto[i].getValore();
		return sum;
	}
	
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ");
		for (int i = 0; i < logicalSize ; i++) 
			sj.add(contenuto[i].toString());
		
		return sj.toString();
	}
	
}


