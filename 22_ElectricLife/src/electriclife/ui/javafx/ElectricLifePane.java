package electriclife.ui.javafx;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

import electriclife.model.Bolletta;
import electriclife.model.Tariffa;
import electriclife.persistence.BollettaWriter;
import electriclife.persistence.MyBollettaWriter;
import electriclife.ui.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ElectricLifePane extends BorderPane {

	private TextArea outputArea;
	private TextField campoConsumo;
	private Controller controller;
	private Button buttonCalcola, buttonStampa;
	private VBox leftPane;
	private Bolletta bolletta;
	private ComboBox<Tariffa> comboTariffe;
	private DatePicker picker; 
	
	public ElectricLifePane(Controller controller) {
		this.controller = controller;
		//
		leftPane = new VBox();
		leftPane.getChildren().add(new Label("Tariffe disponibili"));
		comboTariffe = new ComboBox<>(controller.getTariffe());
		comboTariffe.setValue(comboTariffe.getItems().get(0));
		leftPane.getChildren().add(comboTariffe);
		//
		leftPane.getChildren().add(new Label("Consumo in KWh"));
		campoConsumo = new TextField();
		campoConsumo.setPrefWidth(150);
		campoConsumo.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		campoConsumo.setAlignment(Pos.CENTER_RIGHT);
		leftPane.getChildren().add(campoConsumo);
		leftPane.getChildren().add(new Label("Data di emissione"));
		//
		picker = new DatePicker();
		picker.setPrefWidth(250);
		picker.setValue(LocalDate.now());
		leftPane.getChildren().add(picker);
		this.setLeft(leftPane);

		outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setPrefWidth(600);
		outputArea.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		outputArea.setText("");
		this.setRight(outputArea);
		
		buttonCalcola = new Button("Calcola");
		buttonCalcola.setOnAction(this::calcolaBolletta);
		TilePane commandPane = new TilePane();
		commandPane.setAlignment(Pos.CENTER);
		buttonStampa = new Button("Stampa");
		buttonStampa.setDisable(true);
		buttonStampa.setOnAction(this::stampaBolletta);
		commandPane.getChildren().addAll(buttonCalcola, buttonStampa);
		this.setBottom(commandPane);
	}

	private void calcolaBolletta(ActionEvent event) {
		String consumoText = this.campoConsumo.getText();
		try {
			int consumo = Integer.parseInt(consumoText);
			bolletta = controller.creaBolletta(	picker.getValue(), 
											this.comboTariffe.getValue().getNome(), 
											consumo);
			outputArea.setText(bolletta.toString());
			buttonStampa.setDisable(false);
		}
		catch(NumberFormatException e) {
			Controller.alert("Errore formato numerico", "Consumo errato", "Il consumo in KWh dev essere un numero intero");
			// bolletta immodificata
		}
	}

	private void stampaBolletta(ActionEvent event) {
		try (PrintWriter writer = new PrintWriter("Bolletta.txt")) {
			BollettaWriter bollettaWriter = new MyBollettaWriter(writer);
			controller.stampaBolletta(bolletta, bollettaWriter);
			buttonStampa.setDisable(true);
		} catch (FileNotFoundException e) {
			Controller.alert("Errore di stampa", "Percorso non trovato", "Impossibile stampare la bolletta");
		}
	}

}
