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
		return "Media [title=" + title + ", year=" + year + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Media other = (Media) obj;
		if (obj instanceof Media)
			return this.title == other.title && this.year == other.year;
		return false;
	}
	
	
}
