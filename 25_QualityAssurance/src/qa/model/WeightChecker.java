package qa.model;

import static org.junit.Assert.assertArrayEquals;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WeightChecker extends Checker{

	public WeightChecker(List<Misura> elencoMisure) {
		super(elencoMisure);
	}

	@Override
	public Map<String, Double> getTabellaPercentuali() {
		Map<String, Double> res = new HashMap<>();
 		for (int i = 0; i < getNumeroMisure(); i++) {
			Misura misura = this.getElencoMisure().get(i);
			String nome = misura.getName();
			double perc = (100/misura.getReal())*(misura.getReal()-misura.getExpected());
			if (this.verificaScostamentoPercentuale(misura.getReal(), misura.getExpected(), perc))
				res.put(nome, perc);
		}
		return res;
	}

	@Override
	public String printTabellaPercentuali() {
		NumberFormat format = NumberFormat.getInstance(Locale.ITALY);
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		StringBuilder builder = new StringBuilder();
		
		builder.append("Tabella percentuali: \n");
		for (int i = 0; i < this.getTabellaPercentuali().size(); i++) {
			String[] nomi = this.getTabellaPercentuali().keySet().toArray(new String[this.getTabellaPercentuali().size()]);
			builder.append(nomi[i]);
			builder.append("\t");
			builder.append(format.format(this.getTabellaPercentuali().get(nomi[i])) + " %");
			builder.append(System.lineSeparator());
		}
		
		return builder.toString();
	}

	/* FINOA50(  50, 9.0, '%'), FINOA100(100, 4.5, 'g'), 
	FINOA200(200, 4.5, '%'), FINOA300(300, 9.0, 'g'), 
	FINOA500(500, 3.0, '%'), FINOA1000(1000, 15, 'g'), 
	OLTRE1000(1000, 1.5, '%'); */
	
	@Override
	public List<Misura> getListaMisureInRange(String descrizione) {
		List<Misura> res = new ArrayList<>();
		List<Misura> all = this.getMisure(descrizione);
		for (int i = 0; i < all.size(); i++) {
			Misura misura = all.get(i);
			double delta = (100/misura.getReal())*(misura.getReal()-misura.getExpected());
			if (misura.getExpected() - misura.getReal() > 0)
				res.add(misura);
			else {
				if (misura.getExpected() <= 50.0)
					
					
				
				
				
				
				
				
			}
		}

		return null;
	}
	
	

	@Override
	public double getPercentualeMisureInRange(String descrizione) {
		// TODO Auto-generated method stub
		return 0;
	}

}
