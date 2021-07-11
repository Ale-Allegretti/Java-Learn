package minirail.model;

import java.text.NumberFormat;
import java.util.Map;



public class MyRunner implements Runner {

	private Gauge gauge;
	private Map<String, LineStatus> lines;
	private StringBuilder logger;
	
	public MyRunner(Map<String, LineStatus> lines, Gauge gauge) {
		this.lines = lines;
		this.gauge = gauge;
		this.logger = new StringBuilder();
	}

	
	@Override
	public void clock(double period) {
		this.lines.keySet().stream().
							forEach(linea -> lines.get(linea).getAllTrains().stream().
							forEach(train -> moveTrain(lines.get(linea), train, period)));
	}
	
	private void moveTrain(LineStatus lineStatus, Train train, double period) {
		double pos = lineStatus.getTrainLocation(train);
		double space = period*gauge.kmhToCms(train.getSpeed());
		double newpos = (pos+space) % lineStatus.getLine().getLength(); 
		boolean result = lineStatus.moveTrain(train, newpos);
		
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumFractionDigits(2); formatter.setMinimumFractionDigits(2);
		logger.append("Trying to move " + train.getName() + " running at " + formatter.format(train.getSpeed()) + " km/h " + 
					  "from pos " + formatter.format(pos) + " to pos " + formatter.format(newpos) + ": " + 
					  (result ? "success" : "failure") + System.lineSeparator());
	}
	

	@Override
	public Map<String, LineStatus> getLines() {
		return lines;
	}

	@Override
	public Gauge getGauge() {
		return gauge;
	}


	@Override
	public String toString() {
		return logger.toString();
	}

	
}
