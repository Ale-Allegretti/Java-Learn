package minesweeper.ui.javafx;


import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import minesweeper.ui.controller.Controller;

public class MinesSweeperPane extends BorderPane {

	private Controller controller;
	private MinesSweeperGrid grid;
	private TextArea output;
	private Button restart;
	private Button save;
	
	public MinesSweeperPane(Controller controller, Stage stage) {
		this.controller = controller;
		
		//
		HBox topBox = new HBox();
		int numMines = controller.getMinesNumber();
		topBox.getChildren().add(new Label("Mines: " + numMines));
		topBox.setAlignment(Pos.TOP_LEFT);
		//
		
		this.output = new TextArea(this.controller.getPlayerMineField());
		this.grid = new MinesSweeperGrid(this.controller, output);
		
		//
		HBox bottomBox = new HBox();
		this.restart = new Button("Restart");
		this.save = new Button("Save");
		bottomBox.getChildren().addAll(save, restart);
		restart.setOnAction(this::restarter);
		save.setOnAction(this::saver);
		bottomBox.setAlignment(Pos.BOTTOM_LEFT);
		
		
		this.setTop(topBox);
		this.setCenter(grid);
		this.setRight(output);
		this.setBottom(bottomBox);
	}
	
	private void restarter(ActionEvent event) {
		this.controller.restart();
		output.setText("");
		grid = new MinesSweeperGrid(controller, output);
		this.setCenter(grid);
	}
	private void saver(ActionEvent event) {
		this.controller.save();
	}
	
	
 }
