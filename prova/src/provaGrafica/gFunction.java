package provaGrafica;

import java.util.function.Function;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class gFunction extends Application {

	static Function<Float, Float> f1 = x -> (float) (2.0 / (Math.PI * Math.sin(1.0 * x))
			+ 2.0 / ((3.0 * Math.PI) * Math.sin(3.0 * x)));

	static Function<Float, Float> f2 = x -> 0.5F * x / (x - 1);
	static Function<Float, Float> f3;

	private int xAxisMin = 7, xAxisMax = 7, yAxisMin = 1, yAxisMax = 1;
	private int larghezza = 500, altezza = 400;
	private float fattoreDiScalaX = larghezza / ((float) xAxisMax - xAxisMin);
	private float fattoreDiScalaY = altezza / ((float) yAxisMax - yAxisMin);

	public void start(Stage stage) {
		stage.setTitle("Grafico di funzione");
		FlowPane panel = new FlowPane();
		Canvas canvas = new Canvas(528, 448);
		panel.getChildren().add(canvas);
		GraphicsContext g = canvas.getGraphicsContext2D();
		doMyGraphics(g, f3);
		Scene scene = new Scene(panel);
		stage.setScene(scene);
		stage.show();
	}

	private void doMyGraphics(GraphicsContext g, Function<Float, Float> f) {
		g.setStroke(Color.BLACK);
		g.strokeRect(0, 0, larghezza - 1, altezza - 1);
		g.setStroke(Color.RED);
		g.strokeLine(0, altezza / 2, larghezza - 1, altezza / 2);
		g.strokeLine(larghezza / 2, 0, larghezza / 2, altezza - 1);
		g.fillText("" + xAxisMin, 5, altezza / 2 - 5); // graduazione assi
		g.fillText("" + xAxisMax, larghezza - 10, altezza / 2 - 5);
		g.fillText("" + yAxisMax, larghezza / 2 + 8, 15);
		g.fillText("" + yAxisMin, larghezza / 2 + 5, altezza - 5);
		g.setStroke(Color.BLUE);
		float fXAxisMin = f.apply(Float.valueOf(xAxisMin));
		float xPrev = xAxisMin, yPrev = fXAxisMin;
		setPixel(g, xAxisMin, fXAxisMin);
		for (int ix = 1; ix < g.getCanvas().getWidth(); ix++) {
			float x = xAxisMin + ((float) ix) / fattoreDiScalaX;
			float y = f.apply(x);
			setPixel(g, x, y); // OPPURE: setLine g,xPrev,yPrev,x,y ); SE f CONTINUA
			xPrev = x;
			yPrev = y;
		}

	}

	void setPixel(GraphicsContext g, float x, float y) {
		if (x < xAxisMin || x > xAxisMax || y < yAxisMin || y > yAxisMax)
			return;
		int ix = Math.round((x - xAxisMin) * fattoreDiScalaX);
		int iy = altezza - Math.round((y - yAxisMin) * fattoreDiScalaY); // Trasformazione di coordinate e accensione
																			// singolo pixel (ix, iy)
		g.strokeLine(ix, iy, ix, iy);
	}

	void setLine(GraphicsContext g, float xP, float yP, float x, float y) {
		if (x < xAxisMin || x > xAxisMax || y < yAxisMin || y > yAxisMax || xP < xAxisMin || xP > xAxisMax
				|| yP < yAxisMin || yP > yAxisMax)
			return;
		int ix = Math.round((x - xAxisMin) * fattoreDiScalaX);
		int iy = altezza - Math.round((y - yAxisMin) * fattoreDiScalaY);
		int ixP = Math.round((xP - xAxisMin) * fattoreDiScalaX);
		int iyP = altezza - Math.round((yP - yAxisMin) * fattoreDiScalaY);
		g.strokeLine(ixP, iyP, ix, iy);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
