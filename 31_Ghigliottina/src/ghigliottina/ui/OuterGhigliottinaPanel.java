package ghigliottina.ui;

import ghigliottina.model.Ghigliottina;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class OuterGhigliottinaPanel extends BorderPane {
 
	private GhigliottinaPanel gPanel;
	private TextField txtRispostaUtente, txtRispostaEsatta;
	private Label rightLabel, leftLabel;
	private Button svela;
	private String rispostaEsatta;
	private Controller controller;
	private Ghigliottina gh;
	private int montepremi;
	
	public OuterGhigliottinaPanel(int montepremi, Controller controller) {
		this.controller=controller;
		this.montepremi=montepremi;
		setupGhigliottinaPanel();
		//
		HBox revealBox = new HBox();
		revealBox.setAlignment(Pos.CENTER);
		revealBox.setStyle("-fx-background-color: blue;");
		
		VBox vBox1 = new VBox();
		txtRispostaEsatta = new TextField("");
		txtRispostaEsatta.setAlignment(Pos.BOTTOM_LEFT);
		txtRispostaEsatta.setEditable(false);
		leftLabel = new Label("La parola segreta: ");
		leftLabel.setTextFill(Color.WHITE);
		leftLabel.setAlignment(Pos.TOP_LEFT);
		vBox1.getChildren().addAll(leftLabel, txtRispostaEsatta);
		
		
		
		VBox vBox2 = new VBox();
		txtRispostaUtente = new TextField("");
		txtRispostaUtente.setAlignment(Pos.BOTTOM_CENTER);
		rightLabel = new Label("La tua risposta: ");
		rightLabel.setTextFill(Color.WHITE);
		rightLabel.setAlignment(Pos.TOP_CENTER);
		vBox2.getChildren().addAll(rightLabel, txtRispostaUtente);
		
		
		
		svela = new Button("SVELA");
		svela.setTextFill(Color.RED);
		svela.setAlignment(Pos.BASELINE_RIGHT);
		svela.setPrefHeight(40);
		svela.setOnAction(this::svela);
		
		revealBox.getChildren().addAll(vBox1, vBox2, svela);
		//
		
		this.setTop(revealBox);
	}
	
	private void setupGhigliottinaPanel() {
		// initial setup
		gh = controller.sorteggiaGhigliottina();
		this.rispostaEsatta=gh.getRispostaEsatta();
		gPanel = new GhigliottinaPanel(montepremi, gh.getTerne());
		this.setBottom(gPanel);
	}
	
	private void reset() {
		setupGhigliottinaPanel();
		txtRispostaUtente.setText("");
		txtRispostaEsatta.setText("");
	}
	

	private void svela(ActionEvent e) {
		String rispUtente = this.txtRispostaUtente.getText();
		rispostaEsatta = gh.getRispostaEsatta();
		if (rispUtente.isBlank()) {
			alert("Inserire risposta", "Risposta assente", "Inserire una risposta per continuare!");
			return;
		}
		if (rispUtente.equalsIgnoreCase(rispostaEsatta)) {
			info("Risposta esatta!", "Montepremi vinto", 
					"Complimenti, la risposta è esatta\nMontepremi vinto: " + this.gPanel.getMontepremi());
			this.txtRispostaEsatta.setText(rispostaEsatta);
			reset();
			return;
		}
		else {
			this.txtRispostaEsatta.setText(rispostaEsatta);
			info("Risposta errata", "Risposta errata", "La risposta esatta era: "+ rispostaEsatta);
			reset();
			return;
		}
	}


	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}
