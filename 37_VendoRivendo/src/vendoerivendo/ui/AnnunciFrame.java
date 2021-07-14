package vendoerivendo.ui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vendoerivendo.controller.Controller;
import vendoerivendo.model.Estetica;
import vendoerivendo.model.Prodotto;

public class AnnunciFrame extends BorderPane {

	private Controller controller;
	
	public AnnunciFrame(Controller controller) {
		this.controller = controller;
		//
		VBox vBoxMain = new VBox();
		vBoxMain.setPadding(new Insets(3, 10, 10, 10));
		vBoxMain.setAlignment(Pos.BASELINE_CENTER);
		Label topLabel = new Label("Prodotti:");
		ObservableList<Prodotto> X = FXCollections.observableArrayList(this.controller.getListaProdotti());
		ComboBox<Prodotto> comboBox1 = new ComboBox<Prodotto>(X);
		comboBox1.setPrefSize(500, 20);
		comboBox1.setEditable(false);
		vBoxMain.getChildren().addAll(topLabel, comboBox1);
		//
		HBox centerBox = new HBox();
		centerBox.setAlignment(Pos.TOP_CENTER);
		centerBox.setPadding(new Insets(20, 0, 0, 0));
		Label centerLabel1 = new Label("Estetica: ");
		ObservableList<Estetica> Y = FXCollections.observableArrayList(List.of(Estetica.values()));
		ComboBox<Estetica> comboBox2 = new ComboBox<Estetica>(Y);
		Label centerLabel2 = new Label("Sconto(%): ");
		TextArea area1 = new TextArea();
		area1.setPrefSize(80, 20);
		centerBox.getChildren().addAll(centerLabel1, comboBox2, centerLabel2, area1);
		vBoxMain.getChildren().add(centerBox);
		//
		Button buttonAnnuncio = new Button("Genera annuncio");
		Label centerLabel3 = new Label("Annuncio: ");
		centerLabel3.setPadding(new Insets(10, 0, 0, 0));
		TextArea area2 = new TextArea();
		area2.setPrefSize(0, 0);
		Button buttonStampa = new Button("Stampa Annuncio");
		VBox.setMargin(buttonStampa, new Insets(10, 10, 10, 10));
		vBoxMain.getChildren().addAll(buttonAnnuncio, centerLabel3, area2, buttonStampa);
		
		this.setCenter(vBoxMain);
	}
	
	
}
