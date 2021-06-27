package edicecream.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


import edicecream.model.IceCream;
import edicecream.model.IceCreamKind;
import edicecream.model.IceCreamShop;
import edicecream.persistence.IceCreamKindsRepository;
import edicecream.persistence.IceCreamRepository;
import edicecream.persistence.StockWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyController implements Controller {

	private IceCreamKindsRepository tipoGelati;
	private IceCreamRepository scorteGelati;
	private IceCreamShop shopGelati;
	private StockWriter stockWriter;
	
	
	public MyController(IceCreamKindsRepository tipoGelati, IceCreamRepository scorteGelati, IceCreamShop shopGelati,
			StockWriter stockWriter) {
		this.tipoGelati = tipoGelati;
		this.scorteGelati = scorteGelati;
		this.shopGelati = shopGelati;
		this.stockWriter = stockWriter;
	}
	
	@Override
	public Set<String> getFlavors() {
		return scorteGelati.getFlavors().stream()
				.collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public Set<String> getKindNames() {
		return tipoGelati.getKindNames().stream()
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	private IceCream createIceCream(String kind, List<String> flavors) {
		IceCreamKind icKind = tipoGelati.getIceCreamKind(kind);
		if (icKind == null) 
			return null;

		if (flavors.size() > icKind.getMaxFlavors()) 
			return null;
		
		IceCream rv = new IceCream(icKind, flavors);

		for (String fl : flavors)
			if (scorteGelati.getAvailableQuantity(fl) < rv.getFlavorWeight()) 
				return null;
				
		return rv;
	}

	@Override
	public boolean addIceCream(String kind, List<String> flavors) {
		IceCream ic = createIceCream(kind, flavors);
		if (ic == null) {
			return false;
		}

		for (String fl : flavors)
			scorteGelati.removeQuantity(fl, ic.getFlavorWeight());
		
		shopGelati.addIceCream(ic);

		return true;
	}

	@Override
	public void printReport() {
		try {
		stockWriter.printStock(new PrintWriter("print.txt"), scorteGelati);
		}
		catch (IOException e) {
			alert("Errore", "Scrittura Report", "Impossibile scrivere il file di report.");
		}
	}
	
	private void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	@Override
	public int getMaxFlavors(String flavor) {
		IceCreamKind kind = tipoGelati.getIceCreamKind(flavor);
		if (kind == null) 
			return 0;
		
		return kind.getMaxFlavors();
	}

	@Override
	public String getIceCreamStatus() {
		StringBuilder str = new StringBuilder();
		str.append("Totale gelati venduti: " + shopGelati.getIceCreamCount() + "\n");
		for(String fl: getFlavors())
			str.append("Venduti " + shopGelati.getTotalFlavorConsumption(fl) + "gr di " + fl + "\n");	
		
		str.append("Totale incasso: " + shopGelati.getTotalIncome() + " euro\n");
		return str.toString();
	}

}
