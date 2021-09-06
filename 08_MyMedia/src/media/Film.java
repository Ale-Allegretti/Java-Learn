package media;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Film extends Media implements HasDuration, HasGenre {

	private String[] actors = null;
	private String director = null;
	private int duration = -1; 
	private String genre = null;
	
	public Film (String titolo, int anno, String regista, int duration, String[] attori, String genre) {
		super(anno, titolo);
		this.director = regista;
		this.duration = duration;
		this.genre = genre;
		this.actors = new String[attori.length];
		for (int i = 0; i < attori.length; i++) {
			this.actors[i] = attori[i];
		}
	}

	public String[] getActors() {
		String[] actors = new String[this.actors.length];
		for (int i = 0; i < this.actors.length; i++) {
			actors[i] = this.actors[i];
		}
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = new String[actors.length];
		for (int i = 0; i < actors.length; i++) {
			this.actors[i] = actors[i];
		}
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
		String str = super.toString() + " regista: " + getDirector() + " durata: minuti " + getDuration() + " attori: ";
		for (int i = 0; i < this.actors.length; i++) {
			str += actors[i];
			if (i < this.actors.length - 1) {
				str += ", ";
			}
		}
		str += "\n";
		return str;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Film f){
			return this.getDirector().equals(f.getDirector()) && 
					this.getDuration() == f.getDuration() &&
					this.getGenre() == f.getGenre() &&
					super.equals(obj);}
		return false;
	}
	
	
	
}
