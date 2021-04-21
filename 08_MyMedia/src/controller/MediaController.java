package controller;

import media.*;
import media.collection.MediaCollection;
import media.filters.Filter;

public class MediaController {
	private MediaCollection allMedias = null;
	
	public MediaController() {
		this.allMedias = new MediaCollection();
	}
	
	public boolean add(Media m) {
		int index = this.allMedias.indexOf(m);
		if(index == -1) {
			allMedias.add(m);
			return true;
		}
		return false;
	}
	
	public boolean remove(Media m) {
		int index = this.allMedias.indexOf(m);
		if(index != -1) {
			this.allMedias.remove(index);
			return true;
		}
		return false;
	}
	
	public MediaCollection getAll() {
		return this.allMedias;
	}
	
	public MediaCollection find (Filter f) {
		MediaCollection res = new MediaCollection();
		for (int i = 0; i < this.allMedias.size(); i++) {
			Media m = this.allMedias.get(i);
			if(f.filter(m))
				res.add(m);
		}
		return res;
	}
	
	
	
}
