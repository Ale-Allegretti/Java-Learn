package media;

import java.util.Arrays;
import java.util.Objects;

import media.filters.HasGenre;
import utils.StringUtils;

public class Ebook extends Media implements HasGenre {
	private String[] authors = null;
	private String genre = null;
	
	public Ebook (String titolo, int anno, String[] authors, String genre) {
		super(anno, titolo);
		this.authors = Arrays.copyOf(authors, authors.length);
		this.genre = genre;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Ebook [authors=" + Arrays.toString(authors) + ", genre=" + genre + ", getTitle()=" + getTitle()
				+ ", getYear()=" + getYear() + "]";
	}

	@Override
	public Type getType() {
		return Type.EBOOK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(authors);
		result = prime * result + Objects.hash(genre);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ebook) {
			Ebook other = (Ebook) obj;
			return StringUtils.areEquivalent(authors, other.authors) && this.genre.equalsIgnoreCase(other.genre) && super.equals(other);
		}
		return false;
	}
	

	
}