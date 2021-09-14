package concorrenza;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.RecursiveTask;

public class GetSitePageForJoin extends RecursiveTask<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	
	public GetSitePageForJoin(String url) {
		this.url = url;
	}
	
	@Override
	protected String compute() {
		
		try {
			URL u = new URL(url);
			URLConnection con = u.openConnection();
			InputStream is = con.getInputStream();
			return Utils.getString(is);  
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
