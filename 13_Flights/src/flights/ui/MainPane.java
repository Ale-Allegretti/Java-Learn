package flights.ui;

import java.time.LocalDate;
import java.util.*;

import flights.controller.*;
import flights.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane {
	private ComboBox<Airport> arrivalAirportsComboBox;
	private ComboBox<Airport> departureAirportsComboBox;
	private Controller controller;
	private DatePicker departureDatePicker;
	private FlightScheduleListPanel flightScheduleListPanel;
	
	
	public MainPane(Controller controller) {
		this.controller = controller;	
		flightScheduleListPanel = new FlightScheduleListPanel();
		this.initPane();
		
		VBox vboxleft = new VBox(4);
		{
			vboxleft.setCursor(Cursor.CROSSHAIR);
			vboxleft.setSpacing(10);
			vboxleft.setPadding(new Insets(0, 20, 10, 20));
			Label label1 = new Label("Departure Airports");
			vboxleft.getChildren().add(label1);
			vboxleft.getChildren().add(departureAirportsComboBox);
			Label label2 = new Label("Arrival Airports");
			vboxleft.getChildren().add(label2);
			vboxleft.getChildren().add(arrivalAirportsComboBox);
			Label label3 = new Label("Departure Date");
			vboxleft.getChildren().add(label3);
			vboxleft.getChildren().add(departureDatePicker);
	
			Button searchButton = new Button("find");
			searchButton.opacityProperty().setValue(0.5);
			searchButton.setOnAction(this::myHandle);
			vboxleft.setAlignment(Pos.BASELINE_RIGHT);
			vboxleft.getChildren().add(searchButton);
		}
		
		this.setLeft(vboxleft);
		this.setCenter(flightScheduleListPanel);
		
		
	}
	
	private void initPane() {
		List<Airport> airports = controller.getSortedAirports();
		ObservableList<Airport> observableAirports = FXCollections.observableArrayList(airports);   // crea la collezione osservabile in grafica
		departureAirportsComboBox = new ComboBox<>(observableAirports);
		departureAirportsComboBox.setEditable(false);
		departureAirportsComboBox.setValue(observableAirports.get(10));	//imposta come selezionato il primo elemento
		arrivalAirportsComboBox = new ComboBox<>(observableAirports);
		arrivalAirportsComboBox.setEditable(false);
		arrivalAirportsComboBox.setValue(observableAirports.get(5));
		
		departureDatePicker = new DatePicker(LocalDate.now());
		departureDatePicker.setValue(LocalDate.now());
		
	}
	
	private void myHandle(ActionEvent event) {
		LocalDate date = departureDatePicker.getValue();
		if (date == null) 
			return;
		Airport departure = departureAirportsComboBox.getValue();
		if (departure == null) 
			return;
		Airport arrival = arrivalAirportsComboBox.getValue();
		if (arrival == null) 
			return;
		
		List<FlightSchedule> foundFlights = new ArrayList<FlightSchedule>(this.controller.searchFlights(departure, arrival, date));
		flightScheduleListPanel.setFlightSchedules(foundFlights);
	}
	
}
