package media;

import java.util.Arrays;

import media.filters.HasDuration;
import media.filters.HasGenre;
import utils.StringUtils;

public class Film extends Media implements HasDuration, HasGenre {

	private String[] actors = null;
	private String director = null;
	private int duration = -1; 
	private String genre = null;
	
	public Film (String titolo, int anno, String regista, int duration, String[] attori, String genre) {
		super(anno, titolo);
		this.actors = Arrays.copyOf(attori, attori.length);
		this.director = regista;
		this.duration = duration;
		this.genre = genre;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public Type getType() {
		return Type.FILM;
	}

	@Override
	public String toString() {
		return "Film [actors=" + Arrays.toString(actors) + ", director=" + director + ", duration=" + duration
				+ ", genre=" + genre + ", getTitle()=" + getTitle() + ", getYear()=" + getYear() + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Film) {
			Film other = (Film) obj;
			return StringUtils.areEquivalent(actors, other.actors) && this.director.equalsIgnoreCase(other.director)
					&& duration == other.duration && this.genre.equalsIgnoreCase(other.genre) && super.equals(other);
		}
		return false;
	}
	
	
	
}
