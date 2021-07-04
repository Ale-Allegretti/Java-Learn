package crosswords.model;

import java.util.*;


public class MyGame extends Scheme implements Game {

	private Map<Integer, Optional<String>> charMap;
	
	public MyGame(int size) {
		super(size);
		charMap = new HashMap<>();
		setMapping(0, "#");
	}

	public void setCellRow(int row, int[] numValues) {
		super.setCellRow(row, numValues);
		for (int col = 0; col < getSize(); col++) {
			if(this.charMap.get(numValues[col]) == null) 			
					this.charMap.put(numValues[col], Optional.empty());
		}
	}
	
	
	@Override
	public void setMapping(int num, String value) {
		this.charMap.put(num, Optional.of(value));
	}

	@Override
	public Optional<String> getMapping(int num) {
		if(this.charMap.get(num) == null) {
			return Optional.empty();
		}
		else return this.charMap.get(num);
	}

	@Override
	public boolean isFull() {
		return !this.charMap.containsValue(Optional.empty());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < getSize()*getSize(); k++) {
			int numValue = this.getCell(k/getSize(), k%getSize());
			sb.append(getMapping(numValue).orElse(numValue + ""));
			sb.append(k%getSize() == getSize()-1 ? System.lineSeparator() : '\t');
		}
		return sb.toString();

	}
	
	

}
