package rfd.ui;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import rfd.controller.Controller;
import rfd.model.Route;


public class MainPane extends BorderPane {
	
	private TextArea outputArea; 
	private ComboBox<String> comboFrom, comboTo;
	private Controller controller;
	private CheckBox senzaCambi, unCambio;

	public MainPane(Controller controller) {
		this.controller=controller;
		HBox topBox = new HBox();
		topBox.setPrefHeight(40);
		// populate combos
		comboFrom = new ComboBox<>(); populateCombo(comboFrom);
		comboTo   = new ComboBox<>(); populateCombo(comboTo);
		comboFrom.setOnAction(this::reset);
		comboTo.setOnAction(this::reset);
		topBox.getChildren().addAll(new Label("Partenza "), comboFrom, new Label("  Arrivo "), comboTo);
		this.setTop(topBox);
		//
		HBox centerBox = new HBox();
		centerBox.setPrefHeight(80);
		centerBox.setAlignment(Pos.CENTER);
		senzaCambi = new CheckBox("senza cambi");
		unCambio = new CheckBox("con un cambio");
		centerBox.getChildren().addAll(new Label("Mostra soluzioni:    "), senzaCambi, new Label("   "), unCambio);
		this.setCenter(centerBox);
		senzaCambi.setOnAction(this::myHandle); 
		unCambio.setOnAction(this::myHandle); 
		outputArea = new TextArea();
		outputArea.setPrefSize(500,300);
		outputArea.setFont(Font.font("Courier New", 12));
		this.setBottom(outputArea);
	}

	private void populateCombo(ComboBox<String> combo) {
		combo.setItems(FXCollections.observableArrayList
				(controller.getStationNames().stream().sorted().collect(Collectors.toList()))); 
	}
	
	private void reset(ActionEvent event) {
		outputArea.setText("");
		senzaCambi.setSelected(false);
		unCambio.setSelected(false);
	}
	
	protected void myHandle(ActionEvent event) {
		if (comboFrom.getValue().isEmpty() || comboTo.getValue().isEmpty()) {
			reset(event);
			return;
		}
		if (senzaCambi.isSelected() && !unCambio.isSelected()) {
			outputArea.setText("");
			this.findDirectRoutes(comboFrom.getValue(), comboTo.getValue());
		}
		if (unCambio.isSelected() && !senzaCambi.isSelected()) {
			outputArea.setText("");
			this.findIndirectRoutes(comboFrom.getValue(), comboTo.getValue());
		}
		if (unCambio.isSelected() && senzaCambi.isSelected()) {
			outputArea.setText("");
			this.findDirectRoutes(comboFrom.getValue(), comboTo.getValue());
			this.findIndirectRoutes(comboFrom.getValue(), comboTo.getValue());
		}
		if (!unCambio.isSelected() && !senzaCambi.isSelected()) {
			outputArea.setText("");
		}
		
	}
	
	protected void findDirectRoutes(String from, String to) {
		List<Route> directRoutes = this.controller.getDirectRoutes(from, to);
		StringBuilder builder = new StringBuilder();
		builder.append("PERCORSI DIRETTI: "+System.lineSeparator());
		directRoutes.stream().forEach(p -> builder.append(p.toString() + System.lineSeparator()));
		outputArea.appendText(builder.toString());
	}
	protected void findIndirectRoutes(String from, String to) {
		StringBuilder builder = new StringBuilder();
		List<Route> indirectRoutes = this.controller.getIndirectRoutes(from, to);
		builder.append("PERCORSI INDIRETTI: "+System.lineSeparator());
		indirectRoutes.stream().forEach(p -> builder.append(p.toString() + System.lineSeparator()));
		outputArea.appendText(builder.toString());
	}

}
