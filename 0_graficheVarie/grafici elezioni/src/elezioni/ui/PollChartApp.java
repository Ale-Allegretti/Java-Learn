package elezioni.ui;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

public class PollChartApp extends Application {

	public void start(Stage stage){
		stage.setTitle("Risultati elezioni comunali");
		FlowPane panel = new FlowPane();  // analogo a FLowLayout di Swing

		CategoryAxis asseOrizz = new CategoryAxis(); asseOrizz.setLabel("Comuni");
		NumberAxis asseVert = new NumberAxis(); asseVert.setLabel("Voti");

		//LineChart<String,Number> chart = new LineChart<>(asseOrizz,asseVert);
		BarChart<String,Number> chart = new BarChart<>(asseOrizz,asseVert);
		//ScatterChart<String,Number> chart = new ScatterChart<>(asseOrizz,asseVert);
		chart.setTitle("Elezioni in Emilia-Romagna");
		
		Controller controller = new Controller();
		List<Series<String, Number>> allSeries = controller.getSeries(controller.getRisultato());
		chart.getData().addAll(allSeries);
		
		panel.getChildren().add(chart);
		Scene scene = new Scene(panel);
		stage.setScene(scene);
		stage.show(); 
	}
	
	/*
	--module-path="C:\Program Files\Java\javafx-sdk-11.0.1\lib"
	--add-modules=javafx.controls
	*/
	public static void main(String[] args) {
		launch(args);
	}

}

