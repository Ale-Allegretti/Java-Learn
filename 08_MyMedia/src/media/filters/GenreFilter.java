package media.filters;

import media.Media;

public class GenreFilter implements Filter {
	
	private String genre = null;
	
	public GenreFilter (String genre) {
		setGenre(genre);
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@SuppressWarnings("preview")
	@Override
	public boolean filter(Media media) {
		if (media instanceof HasGenre d)
			return (this.genre == d.getGenre() || this.genre.equalsIgnoreCase(" "));
		return false;
	}
	
}
