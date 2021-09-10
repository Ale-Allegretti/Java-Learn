package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LetturaTastiera {
	public static void main(String[] args) {
        leggi1();    
    }
 
    public static void leggi1() {
        System.out.println("Benvenuto nel programma...");
        String saluto = "Ciao ";
 
        try {
            System.out.println("Inserisci il tuo nome:");
 
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
 
            saluto += bufferRead.readLine();
 
            System.out.println("Inserisci il tuo cognome:");
 
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
 
            saluto += " " + bufferRead.readLine();
 
            System.out.println(saluto);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void leggi2() {
        System.out.println("Benvenuto nel programma...");
 
        String saluto = "Ciao ";
 
        System.out.println("Inserisci il tuo nome:");
        Scanner scanIn = new Scanner(System.in);
        saluto += scanIn.nextLine();
 
        System.out.println("Inserisci il tuo cognome:");
        saluto += " " + scanIn.nextLine();
        
        scanIn.close();
        
        System.out.println(saluto);
    }
}
