package model;

import java.util.Arrays;
import java.util.StringJoiner;

public class AppointmentCollection {
	
		private static final int DEFAULT_GROWTH_FACTOR = 2;
		private static final int DEFAULT_PHYSICAL_SIZE = 10;
		
		private Appointment[] innerContainer;
		private int size;
		
		
		public AppointmentCollection(int physicalSize) {
			this.innerContainer = new Appointment[physicalSize];
			this.size = 0;
		}
		
		public AppointmentCollection() {
			this.innerContainer = new Appointment[DEFAULT_PHYSICAL_SIZE];
			this.size = 0;
		}
		
		
		public int size() {
			return size;
		}
		
		public Appointment get(int k) {
			return innerContainer[k];
		}
		
		public int indexOf(Appointment appointment) {
			for(int i = 0; i < this.size(); i++) {
				if(this.get(i).equals(appointment)) {
					return i;
				}
			}
			return -1;
		}
		
		
		public void add(Appointment f) {
			if (this.size < this.innerContainer.length) {
				this.innerContainer[this.size++] = f;
			}
			else {
			int newLenght = this.innerContainer.length * DEFAULT_GROWTH_FACTOR;
			Appointment[] temp = new Appointment[newLenght];
			int i;
			for (i = 0; i < this.size; i++)
				temp[i] = this.innerContainer[i];
			temp[i++] = f;
			
			this.innerContainer = Arrays.copyOf(temp, temp.length);
			this.size = i;
			}
		}
		
		
		public void remove(int index) {
			Appointment[] temp = new Appointment[this.innerContainer.length];
			int i;
			for (i = 0; i < index; i++)
				temp[i] = this.innerContainer[i];
			for (i = ++index; i < this.size; i++)
				temp[i-1] = this.innerContainer[i];
			
			this.innerContainer = Arrays.copyOf(temp, temp.length);
			this.size = --i;
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
