package edicecream.model;

import java.util.ArrayList;

public class IceCreamShop {
	private ArrayList<IceCream> iceCreams;
	
	public IceCreamShop() {
		iceCreams = new ArrayList<>();
	}
	
	public void addIceCream(IceCream iceCream) {
		this.iceCreams.add(iceCream);
	}

	public float getTotalIncome() {
// stream
		// nota: reduce è necessario per effettuare l'unboxing Float -> float;
		// non servirebbe se esistesse il metodo mapToFloat corrispondente ai mapToInt e mapToDouble
		return iceCreams.stream()
				.map(ic -> ic.getKind().getPrice())
				.reduce(0f, Float::sum);
		
// no stream
//		float ret = 0;
//		for (IceCream iceCream : iceCreams) {
//			ret += iceCream.getKind().getPrice();
//		}
//		return ret;
	}

	public int getTotalFlavorConsumption(String flavor) {
// stream
		return iceCreams.stream()
				.filter(ic -> ic.hasFlavor(flavor))
				.mapToInt(IceCream::getFlavorWeight)
				.sum();
		
// no stream
//		int ret = 0;
//		for (IceCream iceCream : iceCreams) {
//			if (iceCream.hasFlavor(flavor)) {
//				ret += iceCream.getFlavorWeight();
//			}
//		}
//		return ret;
	}

	public int getIceCreamCount() {
		return iceCreams.size();
	}
}

