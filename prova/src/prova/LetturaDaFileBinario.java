package prova;

import java.io.*;

public class LetturaDaFileBinario {

	public static void main(String args[]) {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("file.dat");
		} catch (FileNotFoundException e) {
			System.out.println("File non trovato");
			System.exit(2);
		}

		DataInputStream is = new DataInputStream(fin);
		float f2;
		boolean b2;
		double d2;
		try {
			f2 = is.readFloat();
			b2 = is.readBoolean();
			d2 = is.readDouble();
			System.out.println(b2 + ", " + f2 + ", " + d2);
		} catch (IOException e) {
			System.out.println("Errore di input");
			System.exit(3);
		}

	}

}
