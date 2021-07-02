package conMyLambdaCombo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;

import java.util.function.Function;

import java.util.List;
import java.util.ArrayList;

public class GraficoConMyLambdaRefactoredCombo extends Application {

	static List<NamedFunction<Float,Float>> functions = List.of(
				new NamedFunction<Float, Float>(
						x -> (float) (2.0/Math.PI*Math.sin(1.0*x)+2.0/(3.0*Math.PI)*Math.sin(3.0*x)), "gradino"),
				new NamedFunction<Float, Float>(
						x -> (float) Math.sin(x), "seno"),
				new NamedFunction<Float, Float>(
						x -> 0.5F*x/(x-1), "retta")
			);
	
	private int xAxisMin=-7, xAxisMax=7, yAxisMin=-1, yAxisMax=1;
    private int larghezza=500, altezza=400;
    private float fattoreDiScalaX = larghezza/((float)xAxisMax-xAxisMin);
    private float fattoreDiScalaY = altezza/((float)yAxisMax-yAxisMin);
    private Function<Float,Float> currentFunction;

	public void start(Stage stage){
		stage.setTitle("Flight tracker");
		
		FlowPane panel = new FlowPane();
		panel.setPrefSize(520,448);
		
		Canvas canvas = new Canvas(528, 448);
		panel.getChildren().add(canvas);
		
		ComboBox<NamedFunction<Float,Float>> combo = new ComboBox<>();
		combo.setItems(FXCollections.observableList(functions));
		panel.getChildren().add(combo);
		combo.getSelectionModel().selectFirst();
		currentFunction=combo.getValue();
		combo.setOnAction(event -> {currentFunction=combo.getValue(); replot(canvas);});
       
		replot(canvas);
		Scene scene = new Scene(panel,Color.WHITE); // default size, color WHITE		
		stage.setScene(scene);
		stage.show(); 
	}

	private void replot(Canvas canvas) {
		GraphicsContext g = canvas.getGraphicsContext2D();
		List<Point> points = calcPoints((int)g.getCanvas().getWidth(), currentFunction);
		doMyGraphics(g, points);
	}

	private List<Point> calcPoints(int windowWidth, Function<Float,Float> f){
		List<Point> punti = new ArrayList<>();
		punti.add(new Point(xAxisMin, f.apply(Float.valueOf(xAxisMin))));
		for (int ix=1; ix<windowWidth; ix++){
            float x = xAxisMin+((float)ix)/fattoreDiScalaX;
            punti.add(new Point(x,f.apply(x)));
        }
		return punti;
    }

	private void doMyGraphics(GraphicsContext g, List<Point> punti){
		g.clearRect(0, 0, larghezza-1,altezza-1);
		// cornice
        g.setStroke(Color.BLACK);
		g.strokeRect(0,0,larghezza-1,altezza-1);
        // assi cartesiani
        g.setStroke(Color.RED);
        g.strokeLine(0,altezza/2, larghezza-1,altezza/2);
        g.strokeLine(larghezza/2,0, larghezza/2,altezza-1);
		// graduazione assi
        g.fillText(""+xAxisMin, 5,altezza/2-5);
        g.fillText(""+xAxisMax, larghezza-10,altezza/2-5);
        g.fillText(""+yAxisMax, larghezza/2+5,15);
        g.fillText(""+yAxisMin, larghezza/2+5,altezza-5);
        // disegno funzione
        g.setStroke(Color.BLUE);
		Point pPrev = punti.get(0);
        setPixel(g,pPrev);
		for (Point p : punti) {
            // setPixel(g, p);
            setLine(g,pPrev, p);
			pPrev=p;
		}
    }

    void setPixel(GraphicsContext g, Point p){
		float x = p.getX(), y = p.getY();
        if (x<xAxisMin || x>xAxisMax || y<yAxisMin || y>yAxisMax ) return;
        int ix = Math.round((x-xAxisMin)*fattoreDiScalaX);
        int iy = altezza-Math.round((y-yAxisMin)*fattoreDiScalaY);
        g.strokeLine(ix,iy,ix,iy);
    }

	void setLine(GraphicsContext g, Point pPrev, Point p){
		float x = p.getX(), y = p.getY();
		float xP = pPrev.getX(), yP = pPrev.getY();
        if (x<xAxisMin || x>xAxisMax || y<yAxisMin || y>yAxisMax || xP<xAxisMin || xP>xAxisMax || yP<yAxisMin || yP>yAxisMax ) return;
        int ix = Math.round((x-xAxisMin)*fattoreDiScalaX);
        int iy = altezza-Math.round((y-yAxisMin)*fattoreDiScalaY);
        int ixP = Math.round((xP-xAxisMin)*fattoreDiScalaX);
        int iyP = altezza-Math.round((yP-yAxisMin)*fattoreDiScalaY);
        g.strokeLine(ixP,iyP,ix,iy);
    }

	public static void main(String[] args) {
		launch(args);
	}

}

//------------------

class Point {
	private float x, y;
	
	public Point(float x, float y){
		this.x=x; this.y=y;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String toString(){
		return "(" + x + "," + y + ")";
	}	
}

//------------------

class Pixel {
	private int x, y;
	
	public Pixel(int x, int y){
		this.x=x; this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String toString(){
		return "(" + x + "," + y + ")";
	}
}
