package media;

import utils.StringUtils;

public class Photo extends Media{
	private String[] authors = null;
	
	public Photo (String titolo, int anno, String[] authors) {
		super(anno, titolo);
		this.authors = new String[authors.length];
		for (int i = 0; i < authors.length; i++)
			this.authors[i] = authors[i];
	}

	public String[] getAuthors() {
		String[] authors = new String[this.authors.length];
		for (int i = 0; i < authors.length; i++)
			 authors[i] = this.authors[i];
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = new String[authors.length];
		for (int i = 0; i < authors.length; i++)
			this.authors[i] = authors[i];
	}

	@Override
	public Type getType() {
		return Type.PHOTO;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Photo) {
			Photo other = (Photo) obj;
			return StringUtils.areEquivalent(getAuthors(), other.getAuthors()) && super.equals(other);
		}
		return false;
	}
	
	@Override
	public String toString() {
		String str = super.toString() + " autori: ";
		for (int i = 0; i < this.authors.length; i++) {
			str += authors[i] + " ";
		}
		return str;
	}
	
	

}
