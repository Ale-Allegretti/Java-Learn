package prova;

import java.io.*;

public class ScritturaSuFileBinario {

	public static void main(String args[]) {
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream("file.dat");
		} catch (FileNotFoundException e) {
			System.out.println("Imposs. aprire file");
			System.exit(1);
		}

		DataOutputStream os = new DataOutputStream(fs);
		float f1 = 3.1415F;
		boolean b1 = true;
		double d1 = 1.41;
		try {
			os.writeFloat(f1);
			os.writeBoolean(b1);
			os.writeDouble(d1);
			os.close();
		} catch (IOException ex) {
			System.out.println("Errore di output");
			System.exit(1);
		}
	}

}
