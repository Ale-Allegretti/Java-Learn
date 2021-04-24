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
		this.genre = genre;
		this.authors = new String[authors.length];
		for (int i = 0; i < authors.length; i++) {
			this.authors[i] = authors[i];
		}
	}

	public String[] getAuthors() {
		String[] auth = new String[authors.length];
		for (int i = 0; i < authors.length; i++) {
			auth[i] = authors[i];

		}
		return auth;
	}

	public void setAuthors(String[] authors) {
		this.authors = new String[authors.length];
		for (int i = 0; i < authors.length; i++) {
			this.authors[i] = authors[i];
		}
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		String str = new String(super.toString() + " autori: ");
		for (int i = 0; i < this.authors.length; i++) {
			str = str + authors[i] + " ";

		}
		str = str + "genere: " + getGenre() + "\n";
		return str;

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

	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ebook eb)
		{return StringUtils.areEquivalent(getAuthors(), eb.getAuthors()) && this.genre.equals(eb.getGenre())
				&& super.equals(obj); }
		return false;
	}
	
	
	

	
}