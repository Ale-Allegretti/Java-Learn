package battleship.ui;

import battleship.controller.Controller;
import battleship.model.ShipItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPane extends BorderPane {

	private ComboBox<ShipItem> comboBox;
	private Controller controller;
	private BattleshipGrid grid;
	private Button verifica;
	
	
	public MainPane(Controller controller, Stage stage) {
		this.controller = controller;
		VBox vboxleft = new VBox(15);
		{
			Insets insets = new Insets(5, 20, 10, 20);
			vboxleft.setPadding(insets);
			Label label1 = new Label("Scegli un elemento della combo\n" + 
										"e premi il pulsante nella griglia");
			ObservableList<ShipItem> items = FXCollections.observableArrayList(ShipItem.values());
			comboBox = new ComboBox<>(items);
			comboBox.setValue(items.get(6));
			Label ships = new Label(this.controller.getShipList());
			verifica = new Button("VERIFICA");
			verifica.setAlignment(Pos.BASELINE_RIGHT);
			verifica.setOnAction(this::myHandle);
			vboxleft.getChildren().addAll(label1, comboBox, ships, verifica);
		}
		
		grid = new BattleshipGrid(controller, comboBox);
		
		this.setLeft(vboxleft);
		this.setCenter(grid);
	}
	
	
	private void myHandle(ActionEvent e) {
		int result = this.controller.verify();
		if (result == 0)
			Controller.alert("Esito della verifica", "Nessuna casella errata", "");
		else 
			Controller.alert("Esito della verifica", "Trovate " + result + " caselle errate", "");
		
		if (this.controller.isGameOver()) 
			Controller.alert("Esito della verifica", "Game over", "");
		
		this.grid.updateGridStatus();
	}

}
