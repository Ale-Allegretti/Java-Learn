package media;

import java.util.Arrays;
import utils.StringUtils;

public class Photo extends Media{
	private String[] authors = null;
	
	public Photo (String titolo, int anno, String[] authors) {
		super(anno, titolo);
		this.authors = Arrays.copyOf(authors, authors.length);
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = Arrays.copyOf(authors, authors.length);
	}

	@Override
	public Type getType() {
		return Type.PHOTO;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Photo) {
			Photo other = (Photo) obj;
			return StringUtils.areEquivalent(authors, other.authors) && super.equals(other);
		}
		return false;
	}
	
	
	
	

}
