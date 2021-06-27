package bikerent.ui.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import bikerent.model.Calculator;
import bikerent.model.MyCalculator;
import bikerent.model.MyRent;
import bikerent.model.Rate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyController implements Controller {
	
	private Calculator calculator;
	private Map<String, Rate> mappaTariffe;
	
	public MyController(Map<String, Rate> mappaTariffe) {
		this.mappaTariffe = new HashMap<>(mappaTariffe);
		calculator = new MyCalculator();
	}
	
	@Override
	public ObservableList<String> getCityNames() {
		return FXCollections.observableArrayList(this.mappaTariffe.keySet());
	}

	@Override
	public double getRentCost(String citta, LocalDate dataInizio, LocalTime oraInizio, LocalDate dataFine,
			LocalTime oraFine) {
		return calculator.calc(this.mappaTariffe.get(citta), 
				new MyRent(LocalDateTime.of(dataInizio, oraInizio), LocalDateTime.of(dataFine, oraFine), 
				this.mappaTariffe.get(citta)));
	}

	

}
