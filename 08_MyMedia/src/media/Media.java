package media;

import media.filters.HasType;

public abstract class Media implements HasType {
	private String title = null;
	private int year = -1;
	
	public Media (int year, String title) {
		this.year = year;
		this.title = title;
	}
	
	public abstract Type getType();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	

	@Override
	public String toString() {
		return "Media di tipo " + getType() + " con titolo: " + getTitle() + " anno: " + getYear();
	}
	

	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Media m)
			return (this.title.equals(m.getTitle()) && this.year == m.getYear());
		
		return false;
	}
	
	
}
