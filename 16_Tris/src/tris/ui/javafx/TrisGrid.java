package tris.ui.javafx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tris.ui.controller.Controller;

public class TrisGrid extends GridPane {

	private Button[][] grid = new Button[3][3];  
	private Controller controller;
	private Label player;
	private TextArea output;
	private Button buttonPrint;
	
	public TrisGrid(Controller controller, Label player, TextArea output, Button buttonPrint) {
		this.controller = controller;
		this.player=player;
		this.output=output;
		this.buttonPrint=buttonPrint;
		for(int k=0; k<9; k++) {
			final int x=k/3, y =k%3;
			grid[x][y]=new Button();
			grid[x][y].setStyle("-fx-background-color: WHITE");
			grid[x][y].setFont(Font.font("Courier New", FontWeight.BOLD, 20));
			grid[x][y].setText(" ");
			grid[x][y].setOnAction(ev -> handle(grid[x][y]));
			add(grid[x][y],y,x,1,1);
		}
	}
	
	private void handle(Button button) {
		button.setText(controller.getCurrentPlayer());
		button.setDisable(true);
		boolean winningStatus = controller.storeStatus(this.toString());
			//System.out.println(this.toString() + " " + (res ? "winning" : "continue"));
		if (winningStatus) {
			output.appendText(this.toString() + " VITTORIA "+controller.getCurrentPlayer()+"\n");
			endGame();
		}
		else if (!winningStatus && !this.toString().contains(" ")) {
			output.appendText(this.toString() + " PARTITA PATTA\n");
			endGame();
		}
		else
			output.appendText(this.toString() + " CONTINUA\n");
		controller.getNextPlayer();
		player.setText(controller.getCurrentPlayer());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k=0; k<9; k++) {
			sb.append(grid[k/3][k%3].getText());
		}
		return sb.toString();
	}
	
	public void endGame() {
		for(int k=0; k<9; k++) {
			final int x=k/3, y =k%3;
			grid[x][y].setDisable(true);
		}
		buttonPrint.setDisable(false);
		buttonPrint.setOnAction(ev -> { controller.print(); buttonPrint.setDisable(true); } );
	}

}
