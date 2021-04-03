package fractioncollection;
import frazione.*;
import java.util.Arrays;
import java.util.StringJoiner;


public class FractionCollection {
	
	//private static final int DEFAULT_GROWTH_FACTOR = 2;
	private static final int DEFAULT_PHYSICAL_SIZE = 10;
	
	private Frazione[] innerContainer;
	private int size;
	
	
	public FractionCollection(int physicalSize) {
		this.innerContainer = new Frazione[physicalSize];
		this.size = 0;
	}
	
	public FractionCollection() {
		this.innerContainer = new Frazione[DEFAULT_PHYSICAL_SIZE];
		this.size = 0;
	}
	
	public FractionCollection(Frazione[] collection) {
		this.innerContainer = collection;
		this.size = collection.length;
	
	}
	
	public int size() {
		return size;
	}
	
	public Frazione get(int k) {
		return innerContainer[k];
	}
	
	
	public void put(Frazione f) {
		if (this.size < this.innerContainer.length) {
			this.innerContainer[this.size++] = f;
		}
		else {
		int newLenght = this.innerContainer.length * 2;
		Frazione[] temp = new Frazione[newLenght];
		int i;
		for (i = 0; i < this.size; i++)
			temp[i] = this.innerContainer[i];
		temp[i++] = f;
		
		this.innerContainer = Arrays.copyOf(temp, temp.length);
		this.size = i;
		}
	}
	
	
	public void remove(int index) {
		Frazione[] temp = new Frazione[this.innerContainer.length];
		int i;
		for (i = 0; i < index; i++)
			temp[i] = this.innerContainer[i];
		for (i = ++index; i < this.size; i++)
			temp[i-1] = this.innerContainer[i];
		
		this.innerContainer = Arrays.copyOf(temp, temp.length);
		this.size = --i;
	}
	
	
	public FractionCollection sum(FractionCollection coll) {
		if (this.size != coll.size) 
			return null;
		FractionCollection result = new FractionCollection(this.innerContainer.length);
		for (int i = 0; i < this.size; i++) {
			result.put(this.innerContainer[i].sum(coll.innerContainer[i]));
		}
		return result;
	}
	
	
	public FractionCollection mul(FractionCollection coll) {
		if (this.size != coll.size) 
			return null;
		FractionCollection result = new FractionCollection(this.innerContainer.length);
		for (int i = 0; i < this.size; i++) {
			result.put(this.innerContainer[i].mul(coll.innerContainer[i]));
		}
		return result;
	}
	
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		for (int i = 0; i < this.size; i++) {
			sj.add(this.innerContainer[i].toString());
		}
		
		return sj.toString();
	}


}
