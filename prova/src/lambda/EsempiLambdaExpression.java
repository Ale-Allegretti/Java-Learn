package lambda;

import java.util.concurrent.Callable;

public class EsempiLambdaExpression {
	public static void main(String[] args) {
		Thread t1 = new Thread(){
			@Override
			public void run(){
				System.out.println("Ecco un thread creato senza Lambda Expressions");
			}
		};

		Thread t2 = new Thread(() -> System.out.println("Questo è un thread creato usando le Lambda Expressions...!"));

		Thread t3 = new Thread(() -> {
			int count = 0;
//			continue;
			while(true) {
				if(count % 2 == 0) {
					System.out.println("count vale = " + count);
				}
				
				if(count == 100) {
					break;
				}
				
				count++;
			}
		});
				
		t1.start();
		t2.start();
		t3.start();

		
		@SuppressWarnings("unused")
		Thread t4 = new Thread(()->{
			System.out.println("sono nel thread 4");
			
			int max = 0;
			
			while(max < 10) {
				System.out.println("max = " + max);
				
				if(max == 5) {
					break;
				}
			}
		});
		
		
		String nome = null;
		String cognome = null;
		
		saluta(nome, cognome);
	}
	
	private static void saluta(String nome, String cognome) {
		Callable<String> callMe = () -> {
			if(nome == null && cognome == null) {
				throw new Exception("Il nome ed il cognome non sono stati inseriti");
			} else if(nome == null && cognome != null) {
				throw new Exception("Il nome non � stato inserito");
			} else if(nome != null && cognome == null) {
				throw new Exception("Il cognome non � stato inserito");
			} else {
				return "Ciao " + nome + " " + cognome;
			}
		};
		
		try {
			callMe.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
