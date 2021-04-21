package media;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Song extends Media implements HasDuration, HasGenre{
	
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
		return "Song [duration=" + duration + ", genre=" + genre + ", singer=" + singer + ", getTitle()=" + getTitle()
				+ ", getYear()=" + getYear() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Song) {
			Song other = (Song) obj;
			return this.duration == other.duration && this.genre.equalsIgnoreCase(other.genre) && this.singer.equalsIgnoreCase(other.singer)
					&& super.equals(other);
		}
		return false;
	}
	
	
	
	
	
	

}
