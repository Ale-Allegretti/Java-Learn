package bikerent.ui.javafx;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

import bikerent.ui.controller.Controller;
import javafx.event.ActionEvent;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class RentABikePane extends BorderPane {
	
	private Button bottoneTicket;
	private VBox centerPane;
	private VBox leftPane;
	private ComboBox<String> combo;
	private Controller controller;
	private TextField fieldInizio;
	private TextField fieldFine;
	private DateTimeFormatter formatter;
	private TextArea outputArea;
	private DatePicker pickerFine;
	private DatePicker pickerInizio;
	

	public RentABikePane(Controller controller) {
		this.controller = controller;
		formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
		//
		leftPane = new VBox();
		leftPane.setSpacing(1);
		leftPane.setMaxWidth(BASELINE_OFFSET_SAME_AS_HEIGHT);
		Label label1 = new Label("Città: ");
		combo = new ComboBox<>(this.controller.getCityNames());
		leftPane.getChildren().add(new HBox(label1, combo));
		leftPane.getChildren().add(new Label("Inizio noleggio: "));
		pickerInizio = new DatePicker(LocalDate.now());
		leftPane.getChildren().add(pickerInizio);
		fieldInizio = new TextField();
		fieldInizio.setLayoutX(pickerInizio.getLayoutX());
		fieldInizio.setLayoutY(pickerInizio.getLayoutY());
		fieldInizio.setText(LocalTime.now().format(formatter));
		fieldInizio.setPromptText("Inserire ora iniziale");
		leftPane.getChildren().add(fieldInizio);
	    leftPane.setAlignment(Pos.BASELINE_LEFT);
		//	
		centerPane = new VBox();
		centerPane.setSpacing(1);
		centerPane.setMaxWidth(BASELINE_OFFSET_SAME_AS_HEIGHT);
		centerPane.setMinWidth(BASELINE_OFFSET_SAME_AS_HEIGHT);
		bottoneTicket = new Button("Calcola Costo");
		bottoneTicket.setBackground(new Background(new BackgroundFill(Color.RED, null, getInsets())));
		bottoneTicket.setOnAction(this::calcolaCostoNoleggio);
		HBox botBox = new HBox(bottoneTicket);
		botBox.setAlignment(Pos.TOP_CENTER);
		centerPane.getChildren().add(botBox);
		centerPane.getChildren().add(new Label("Fine noleggio: "));
		pickerFine = new DatePicker(LocalDate.now());
		centerPane.getChildren().add(pickerFine);
		fieldFine = new TextField();
		fieldFine.setLayoutX(pickerFine.getLayoutX());
		fieldFine.setLayoutY(pickerFine.getLayoutY());
		fieldFine.setText(LocalTime.now().format(formatter));
		fieldFine.setPromptText("Inserire ora finale");
		centerPane.getChildren().add(fieldFine);
		centerPane.setAlignment(Pos.BASELINE_LEFT);
		//
		outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setPrefWidth(250);
		outputArea.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		outputArea.setText("");
	
		//bottoneTicket.setOnAction(this::calcolaCostoNoleggio);
		
		
		this.setLeft(leftPane);
		this.setCenter(centerPane);
		this.setRight(outputArea);
		
	}
	
	private void calcolaCostoNoleggio(ActionEvent event) {
		if (fieldInizio.getText().isBlank())
			return;
		if (fieldFine.getText().isBlank())
			return;
		String citta = this.combo.getValue();
		if (citta == null) {
			Controller.alert("Errore", "Impossibile reperire la tariffa", "Non è stata selezionata alcuna città");
			this.outputArea.setText("");
			return;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
		String start = fieldInizio.getText().trim();
		String end = fieldFine.getText().trim();
		try {
			LocalTime partenza = LocalTime.parse(start, formatter);
			LocalTime arrivo = LocalTime.parse(end, formatter);
			if (pickerFine.getValue().isBefore(pickerInizio.getValue()) || arrivo.isBefore(partenza)) {
				Controller.alert("Errore", "Impossibile tornare indietro nel tempo", 
						"L'orario di fine precede quelle di inizio noleggio");
				this.outputArea.setText("");
				return;
			}
			double costo = this.controller.getRentCost(citta, pickerInizio.getValue(), partenza, pickerFine.getValue(), arrivo);
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
			this.outputArea.setText("Costo del noleggio " + currencyFormatter.format(costo));
		} catch (DateTimeParseException e) {
			Controller.alert("Errore di formato", "Errore nel formato orario", 
					"L'orario di inizio e fine noleggio deve essere nella forma HH:MM");
			this.outputArea.setText("");
		}
		
	}
	
}
