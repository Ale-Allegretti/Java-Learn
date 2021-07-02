package noLambda;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GraficoNoLambda extends Application {

	static float f(float x){
		return (float)(2.0/Math.PI*Math.sin(1.0*x)+2.0/(3.0*Math.PI)*Math.sin(3.0*x));
		// return (float) Math.sin(x);
		// return 0.5F*x/(x-1);
    }

	private int xAxisMin=-7, xAxisMax=7, yAxisMin=-1, yAxisMax=1;
    private int larghezza=500, altezza=400;
    private float fattoreDiScalaX = larghezza/((float)xAxisMax-xAxisMin);
    private float fattoreDiScalaY = altezza/((float)yAxisMax-yAxisMin);

	public void start(Stage stage){
		stage.setTitle("Grafico di funzione");

		FlowPane panel = new FlowPane();  // analogo a FLowLayout di Swing
		panel.setPrefSize(520,448);
		Canvas canvas = new Canvas(528, 448);
		panel.getChildren().add(canvas);
        GraphicsContext g = canvas.getGraphicsContext2D();
		doMyGraphics(g);
		Scene scene = new Scene(panel,Color.WHITE); // default size, color WHITE		
		stage.setScene(scene);
		stage.show(); 
	}

	private void doMyGraphics(GraphicsContext g){
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
		float fXAxisMin=f(xAxisMin);
		float xPrev=xAxisMin, yPrev=fXAxisMin; 
        setPixel(g,xAxisMin,fXAxisMin);
        for (int ix=1; ix<g.getCanvas().getWidth(); ix++){
            float x = xAxisMin+((float)ix)/fattoreDiScalaX;
			float y = f(x);
            // setPixel(g,x,y);
            setLine(g,xPrev,yPrev,x,y);
			xPrev=x; yPrev=y;
        }
    }

    void setPixel(GraphicsContext g, float x, float y){
        if (x<xAxisMin || x>xAxisMax || y<yAxisMin || y>yAxisMax ) return;
        int ix = Math.round((x-xAxisMin)*fattoreDiScalaX);
        int iy = altezza-Math.round((y-yAxisMin)*fattoreDiScalaY);
        g.strokeLine(ix,iy,ix,iy);
    }

	void setLine(GraphicsContext g, float xP, float yP, float x, float y){
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
