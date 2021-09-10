package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Scrittura {

	public static void main(String[] args) {
		String dir = "E:\\corso_java\\test-dir\\";

		Scrittura s = new Scrittura();
//		s.esempioFileWriter(dir+"file-writer.txt", "Lorem ipsum....");
		
		s.esempioBufferedFileWriter(
				dir+"buffered-writer.txt", 
				new String[] {"Paolo", "Preite", "Corso Java", "Avanzato!"});
		
	}
	
	public void esempioPrintWriter(String filePath, String[] testo) {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(new FileWriter(getFile(filePath)));
			
			for (String string : testo) {
				pw.write(string);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
	public void esempioBufferedFileWriter(String filePath, String[] testo) {
		
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(getFile(filePath)));
			
			for (String string : testo) {
				bw.write(string);
				bw.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void esempioFileWriter(String filePath, String testo) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(getFile(filePath));
			fw.write(testo);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private File getFile(String filePath) {
		File file = new File(filePath);
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return file;
	}
}
