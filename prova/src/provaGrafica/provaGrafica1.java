package provaGrafica;

import javafx.application.Application;
import javafx.stage.Stage;

public class provaGrafica1 extends Application {
	public void start(Stage stage) {
		stage.setTitle("Esempio 1");
		stage.setWidth(200);
		stage.setHeight(100);
		stage.setX(300);
		stage.setY(300);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
