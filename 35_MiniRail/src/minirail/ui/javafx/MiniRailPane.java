package minirail.ui.javafx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import minirail.model.Train;
import minirail.model.TrainStatus;
import minirail.ui.controller.Controller;

public class MiniRailPane extends BorderPane {

	private Button clock;
	private Button moving;
	private Button stopping;
	private Controller controller;
	private Label label;
	private TextArea output;
	private Map<Train, Color> trainColors;
		
	public MiniRailPane (Controller controller, double[] trainPositions) {
		this.controller = controller;
		//
		trainColors = new HashMap<>();
		drawTrainColors(trainColors);
		//
		HBox b1 = new HBox();
		b1.setSpacing(10);
		moving = new Button("Move trains");
		moving.setOnAction(this::moveTrains);
		stopping = new Button("Stop trains");
		stopping.setOnAction(this::stopTrains);
		label = new Label("Trains are now " + TrainStatus.STOPPED);
		b1.getChildren().addAll(moving, stopping, label);
		//
		output = new TextArea();
		output.setWrapText(true);
		output.setPrefColumnCount(10);
		output.setPrefRowCount(20);
		output.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
		//
		this.setBottom(output);
		//
		HBox rightPane = new HBox();
		clock = new Button("Clock");
		rightPane.getChildren().add(clock);
		clock.setOnAction(this::advanceClock);
		//
		this.setRight(rightPane);
		//		
		setupTrainPanel(trainPositions);
		
		this.setTop(b1);
		
	}
	
	private void advanceClock(ActionEvent event) {
		this.controller.clock(0.5);
		output.appendText(this.controller.getLog());
		updateTrainPanel();
	}
	
	private void moveTrains(ActionEvent event) {
		this.controller.getTrains().forEach(t -> this.controller.setMoving(t));
		label.setText("Trains are now " + TrainStatus.MOVING);
	}

	
	private void stopTrains(ActionEvent event) {
		this.controller.getTrains().forEach(t -> this.controller.setStopped(t));
		label.setText("Trains are now " + TrainStatus.STOPPED);
	}
	
	
	private void setupTrainPanel(double[] trainPositions) {
		TrainLinePane centerPane = new TrainLinePane(this.controller.getLine());
		List<Train> trains = this.controller.getTrains();
		for (int j = 0; j < trains.size(); j++) {
			Train t = trains.get(j);
			boolean positioningOK = controller.getLineStatus().putTrain(t, trainPositions[j]);
			if (positioningOK) { 
				centerPane.drawTrain(trainPositions[j], t.getLength(), trainColors.get(t), t.getName());
			}
		}
		this.setCenter(centerPane);
	}
	
	private void updateTrainPanel() {
		TrainLinePane centerPane = new TrainLinePane(this.controller.getLine());
		for (Train t: this.controller.getTrains()) {
			boolean positioningOK = controller.getLineStatus().putTrain(t, this.controller.getLineStatus().getTrainLocation(t));
			if (positioningOK) { 
				centerPane.drawTrain(this.controller.getLineStatus().getTrainLocation(t), t.getLength(), trainColors.get(t), t.getName());
			}
		}
		this.setCenter(centerPane);
	}
	
	private Color randomColor() {
		Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r,g,b);
	}
	
	private void drawTrainColors(Map<Train, Color> trainColors) {
		for (Train t: this.controller.getTrains()) {
			trainColors.put(t,randomColor());
		}
	}
	
	
}


