package rfd.model;


import java.util.StringTokenizer;


public class MyPointOfInterest extends PointOfInterest {
	
	
	public MyPointOfInterest(String name, String progressivaKm) {
		super(name, progressivaKm);
		if (!progressivaKm.contains("+"))
			throw new IllegalArgumentException("Formato progressiva errato");
		StringTokenizer tokenizer = new StringTokenizer(progressivaKm, "+");
		String kmString = tokenizer.nextToken().trim();
		if (!tokenizer.hasMoreTokens())
			throw new IllegalArgumentException("Formato progressiva errato");
		String mtString = tokenizer.nextToken().trim();

		if (kmString.length() < 1 || kmString.length() > 3 || mtString.length() != 3 )
			throw new IllegalArgumentException("Formato progressiva errato");
	}

	
	@Override
	public double getKmAsNum() {
		if (!this.getKm().contains("+"))
			throw new IllegalArgumentException("Formato progressiva errato");
		StringTokenizer tokenizer = new StringTokenizer(this.getKm(), "+");
		String kmString = tokenizer.nextToken().trim();
		String mtString = tokenizer.nextToken().trim();
		if (kmString.length() < 1 || kmString.length() > 3 || mtString.length() != 3)
			throw new IllegalArgumentException("Formato progressiva errato");
		
		double kmNum = Double.parseDouble(kmString.trim());
		double mtNum = Double.parseDouble(mtString.trim())/1000;
		
		return kmNum + mtNum;
	}

}
