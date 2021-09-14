package thread.sync;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ContoCorrente {
	private static ContoCorrente cc;
	
	public static ContoCorrente getInstance() {
		if(cc == null)
			cc = new ContoCorrente();
		
		return cc;
	}
	
	public double getSaldo() {
		double saldo = 0;
		
		BufferedReader br = null;
		try {
			File fin = new File(new File(".").getCanonicalPath() + File.separator + "db.txt");
			
			br = new BufferedReader(new FileReader(fin));
			 
			String line = null;
			while ((line = br.readLine()) != null) {
				saldo = Double.parseDouble(line);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return saldo;
	}
	
	public synchronized void prelievo(double somma) throws Exception {
		Thread.sleep(5000);

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			double nuovoSaldo = getSaldo() - somma;

			if(nuovoSaldo > 0) {
				fw = new FileWriter(new File(".").getCanonicalPath() + File.separator + "db.txt");
				bw = new BufferedWriter(fw);
				bw.write(nuovoSaldo+"");
			} else
				throw new Exception("Saldo insufficiente!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
	
	public void prelievoNonSync(double somma) throws Exception {
		Thread.sleep(5000);

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			double nuovoSaldo = getSaldo() - somma;

			if(nuovoSaldo > 0) {
				fw = new FileWriter(new File(".").getCanonicalPath() + File.separator + "db.txt");
				bw = new BufferedWriter(fw);
				bw.write(nuovoSaldo+"");
			} else
				throw new Exception("Saldo insufficiente!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
