package proveVarie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ProvaProporties {

	public static void main(String[] args) {
		ProvaProporties prova = new ProvaProporties();
		
		prova.getConfig();
	}

	public void getConfig() {
		Properties properties = new Properties();
		String stampa = "";
		try {
			InputStream is = new FileInputStream(
								new File("C:\\Users\\User\\Desktop\\UNIBO\\4. INFORMATICA JAVA\\Projects\\Java-Learn\\prova\\config.properties"));
			properties.load(is);
			stampa = properties.getProperty("db-name");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(stampa);
		
	}
}
