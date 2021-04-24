package media;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Song extends Media implements HasDuration, HasGenre {
	
	private int duration = -1;
	private String genre = null;
	private String singer = null;
	
	public Song(String titolo, int anno, String singer, int duration, String genre) {
		super(anno, titolo);
		this.duration = duration;
		this.genre = genre;
		this.singer = singer;
	}

	@Override
	public Type getType() {
		return Type.SONG;
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


	public String getSinger() {
		return singer;
	}


	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return super.toString() + " cantante: " + getSinger() + " durata: minuti " + getDuration() + "\n";
	}

	
	@SuppressWarnings("preview")
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Song c) {	
		return getSinger().equals(c.getSinger()) && 
				getDuration() == c.getDuration() &&
				getGenre() == c.getGenre() &&
				super.equals(obj);
		}
		return false;
	}
	
	
	
	
	
	

}
