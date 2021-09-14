package concorrenza;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GetSitePage extends Thread {

	private String url;
	private String content;
	
	
	
	public GetSitePage(String url) {
		this.url = url;
	}



	@Override
	public void run() {
		
		try {
			URL u = new URL(url);
			URLConnection con = u.openConnection();
			InputStream is = con.getInputStream();
			setContent(Utils.getString(is));  // per scrivere su file la pagina web recuperata
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}

	
}
