package edicecream.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Controller {
	Set<String> getFlavors();
	Set<String> getKindNames();
	boolean addIceCream(String kind, List<String> flavors);
	void printReport() throws IOException;
	int getMaxFlavors(String flavor);
	String getIceCreamStatus();
}
