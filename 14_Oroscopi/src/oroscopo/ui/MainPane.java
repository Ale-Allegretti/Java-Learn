package oroscopo.ui;

import java.io.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import oroscopo.controller.AbstractController;
import oroscopo.controller.Mese;
import oroscopo.model.*;

public class MainPane extends BorderPane{
	
	private AbstractController controller = null;
	private int fortunaMin;
	private TextArea output;
	private ComboBox<SegnoZodiacale> segniZodiacali;

	public MainPane(AbstractController controller, int fortunaMin) {
		this.controller = controller;
		this.fortunaMin = fortunaMin;
		this.output = new TextArea();
		
		this.initPane();
	}
	
	private void initPane() {
		ObservableList<SegnoZodiacale> segniZ = FXCollections.observableArrayList(controller.getSegni());
		segniZodiacali = new ComboBox<SegnoZodiacale>(segniZ);
		segniZodiacali.setEditable(false);
		
		VBox vboxleft = new VBox();
		{	
			vboxleft.setSpacing(10);
			vboxleft.setPadding(new Insets(0, 20, 10, 20));
			Label label1 = new Label("Segno zodiacale:");
			vboxleft.getChildren().add(label1);
			vboxleft.getChildren().add(segniZodiacali);
			Label label2 = new Label("Oroscopo mensile del segno:");
			vboxleft.getChildren().add(label2);
		}
		
		output.setEditable(false);
		output.setPrefWidth(200);
		segniZodiacali.setOnAction(e -> output.setText(controller.generaOroscopoCasuale
				(segniZodiacali.getValue()).toString()));
		vboxleft.getChildren().add(output);
		
		Button button = new Button("Stampa annuale");
		button.setOnAction(this::myHandle);
		vboxleft.getChildren().add(button);
		
		this.setCenter(vboxleft);
	}
	
	private void myHandle(ActionEvent event) {
		try (PrintWriter inner = new PrintWriter("OroscopoAnnuale.txt")) {
			StringBuilder sb = new StringBuilder();
			SegnoZodiacale segno = segniZodiacali.getValue();
			Oroscopo[] oroscopi = controller.generaOroscopoAnnuale(segno, this.fortunaMin);
			sb.append(segno.toString() + System.lineSeparator());
			sb.append("------------" + "\n");
			for (int i = 0; i < 12; i++) {
				sb.append(Mese.values()[i] + "\n");
				sb.append(oroscopi[i].toString() + "\n");
				sb.append("\n");
			}
			sb.append("GRADO DI FORTUNA " + this.fortunaMin);
			inner.write(sb.toString());
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
