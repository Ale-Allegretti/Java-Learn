package qa.ui.javafx;

import java.text.NumberFormat;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import qa.ui.controller.Controller;

public class MyQualityPane extends BorderPane {

	private ComboBox<String> combo;
	private Controller controller;
	private FlowPane chartPane;
	private BarChart<String,Number> chart;
	private TextArea areaTabella, areaDettaglio;

	public MyQualityPane(Controller controller) {
		this.controller = controller;
		// --combo & textarea
		VBox comboPane = new VBox();
		combo = new ComboBox<>(controller.getDescrizioni());
		comboPane.getChildren().add(combo);
		combo.setOnAction(this::calcolaPercentuali);
		combo.setPromptText("Selezionare prodotto per i dettagli...");
		this.setTop(comboPane);
		areaDettaglio = new TextArea();
		comboPane.getChildren().add(areaDettaglio); 
		areaDettaglio.setPrefSize(USE_COMPUTED_SIZE, 100);
		areaDettaglio.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		areaTabella = new TextArea(); areaTabella.setPrefSize(USE_COMPUTED_SIZE, 20);
		areaTabella.setFont(Font.font("Verdana", FontWeight.NORMAL, 14));
		comboPane.getChildren().add(areaTabella);
		areaTabella.setText(controller.getChecker().printTabellaPercentuali());		
		//--trigger
		TilePane commandPane = new TilePane();
		commandPane.setAlignment(Pos.CENTER);
		this.setBottom(commandPane);
		chartPane = new FlowPane();
		this.setCenter(chartPane);
		disegnaGrafico();
	}

	private void calcolaPercentuali(ActionEvent event) {	
		String desc = combo.getValue();
		StringBuilder sb = new StringBuilder();
		sb.append("Dettaglio " + desc + System.lineSeparator());
		sb.append("# misure: " + controller.getMisure(desc).size() + System.lineSeparator());
		sb.append("# prodotti entro il range: " + controller.getListaProdottiEntroRange(desc).size() + System.lineSeparator());
		sb.append("% prodotti entro il range: " + NumberFormat.getPercentInstance().format(controller.getPercentualeProdottiEntroRange(desc)) + System.lineSeparator());
		areaDettaglio.setText(sb.toString());		
	}

	private void disegnaGrafico() {
		chartPane.getChildren().remove(chart);
		// creazione nuovo chart
		CategoryAxis asseOrizz = new CategoryAxis();	
		asseOrizz.setLabel("Prodotti");
		NumberAxis asseVert = new NumberAxis();			
		asseVert.setLabel("% Qualità");
		chart = new BarChart<>(asseOrizz,asseVert);
		// popolamento
		for (String desc : controller.getDescrizioni()){
			XYChart.Series<String,Number> serie = new XYChart.Series<String,Number>();
			serie.getData().add(new XYChart.Data<>(desc, 100*controller.getTabellaPercentuali().get(desc)));
			chart.getData().add(serie);
		}	
		chart.setTitle("% prodotti entro standard qualità");
		chart.setPrefWidth(600);
		chart.setLegendVisible(false);
		chartPane.getChildren().add(chart);
	}
}
