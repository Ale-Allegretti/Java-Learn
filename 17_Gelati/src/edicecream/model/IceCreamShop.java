package edicecream.model;

import java.util.ArrayList;
import java.util.List;


public class IceCreamShop {

	private List <IceCream> gelati;

	public IceCreamShop() {
		this.gelati = new ArrayList<>();
	}
	
	public void addIceCream(IceCream icecream) {
		this.gelati.add(icecream);
	}
	
	public float getTotalIncome() {
		float res = 0;
		for(IceCream gelato : this.gelati)
			res += gelato.getKind().getPrice();
		return res;
	}
	
	public int getTotalFlavorConsumption(String flavor) {
		return gelati.stream()
				.filter(ic -> ic.hasFlavor(flavor))
				.mapToInt(IceCream::getFlavorWeight)
				.sum();
	}
	
	public int getIceCreamCount() {
		return this.gelati.size();
	}
	
	
}
