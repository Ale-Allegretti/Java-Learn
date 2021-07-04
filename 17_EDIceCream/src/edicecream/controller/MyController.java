package edicecream.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edicecream.model.IceCream;
import edicecream.model.IceCreamKind;
import edicecream.model.IceCreamShop;
import edicecream.persistence.IceCreamKindsRepository;
import edicecream.persistence.IceCreamRepository;
import edicecream.persistence.StockWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyController implements Controller {
	private IceCreamKindsRepository kindRepo;
	private IceCreamRepository iceRepo;
	private IceCreamShop shop;
	private StockWriter stockWriter;
	
	public MyController(IceCreamKindsRepository kind, IceCreamRepository ice, IceCreamShop shop, StockWriter stockWriter) {
		this.kindRepo = kind;
		this.iceRepo = ice;
		this.shop = shop;
		this.stockWriter = stockWriter;
	}

	@Override
	public Set<String> getFlavors() {
		return new TreeSet<>(iceRepo.getFlavors());
	}

	@Override
	public Set<String> getKindNames() {
		return new TreeSet<>(kindRepo.getKindNames());
	}
	
	private IceCream createIceCream(String kind, List<String> flavors) {
		IceCreamKind icKind = kindRepo.getIceCreamKind(kind);
		if (icKind == null) {
			return null;
		}

		if (flavors.size() > icKind.getMaxFlavors()) {
			return null;
		}
		
		IceCream rv = new IceCream(icKind, flavors);

		for (String fl : flavors) {
			if (iceRepo.getAvailableQuantity(fl) < rv.getFlavorWeight()) {
				return null;
			}
		}

		return rv;
	}
	
	@Override
	public boolean addIceCream(String kind, List<String> flavors) {
		IceCream ic = createIceCream(kind, flavors);
		if (ic == null) {
			return false;
		}

		for (String fl : flavors) {
			iceRepo.removeQuantity(fl, ic.getFlavorWeight());
		}
		shop.addIceCream(ic);

		return true;
	}

	private Writer createReportWriter() throws IOException {
		return new FileWriter("StockGiornaliero.txt");
	}

	public void printReport() {
		try (Writer writer = createReportWriter()) {
			stockWriter.printStock(writer, iceRepo);
		} catch (IOException e) {
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
	public int getMaxFlavors(String name) {
		IceCreamKind kind = kindRepo.getIceCreamKind(name);
		if (kind == null) {
			return 0;
		}
		return kind.getMaxFlavors();
	}

	@Override
	public String getIceCreamStatus() {
		StringBuilder str = new StringBuilder();
		str.append("Totale gelati venduti: "+ shop.getIceCreamCount()+"\n");
		for(String fl: getFlavors())
		{
		 str.append("Venduti "+ shop.getTotalFlavorConsumption(fl) + "gr di " +fl+"\n");	
		}
		
		str.append("Totale incasso: "+shop.getTotalIncome()+" euro\n");
		return str.toString();
	}

}
