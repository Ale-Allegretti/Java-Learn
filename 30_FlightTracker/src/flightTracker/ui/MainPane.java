package flightTracker.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import flightTracker.model.Flight;
import flightTracker.persistence.BadFileFormatException;
import flightTracker.ui.controller.Controller;
import flightTracker.ui.controller.MyController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainPane extends BorderPane {

	private GeoCanvas canvas;
	private CheckBox checkbox;
	private Controller controller;
	private ComboBox<String> flightCombo;
	
	public MainPane(GeoMap geoMap, Controller controller) {
		this.controller = controller;
		//
		HBox topBox = new HBox(10);
		topBox.setSpacing(10);
		topBox.setPadding(new Insets(5, 10, 10, 5));
		topBox.getChildren().add(new Label("Voli disponibili: "));
		flightCombo = new ComboBox<>(this.controller.getFlights());
		flightCombo.setTooltip(new Tooltip("Scegliere il volo da graficare"));
		topBox.getChildren().add(flightCombo);
		checkbox = new CheckBox("Voli multipli");	
		topBox.getChildren().add(checkbox);
		topBox.setAlignment(Pos.BASELINE_LEFT);
		//
		FlowPane panel = new FlowPane();
		canvas = new GeoCanvas(geoMap);
		panel.getChildren().add(canvas);
		//
		flightCombo.setOnAction(this::myHandler);
		canvas.drawParallels(List.of(45));
		
		
		
		this.setCenter(panel);
		this.setTop(topBox);
	}
	
	private void plotFlight(Flight flight) {
		if (checkbox.isSelected()) {
			double r,g,b;
			Random rnd = new Random();
			r = rnd.nextDouble(); 
			g = rnd.nextDouble(); 
			b = rnd.nextDouble();
			canvas.setDrawingColor(Color.color(r, g, b));
		}
		else {
			canvas.redrawMap();
		}
		canvas.drawPoints(this.controller.getPoints(flight));
		
	}
	
	public void myHandler(ActionEvent event) {
		String flightName = flightCombo.getValue();
		try {
			Flight flight = controller.load(flightName,new FileReader(flightName));
			plotFlight(flight);		
			}
		catch (IOException e) {
			MyController.alert("Errore", "Errore di I/O nella lettura del file " + flightName, "Addio");
			//System.exit(1);
		} catch (BadFileFormatException e) {
			MyController.alert("Errore", "Errore nel formato del file " + flightName, "Addio");
			//System.exit(1);
		}
		
	}
	
	
}
