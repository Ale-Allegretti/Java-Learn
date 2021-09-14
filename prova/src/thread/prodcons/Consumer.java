package thread.prodcons;

import java.util.List;

public class Consumer implements Runnable {
	private final List<Integer> bufferCondiviso;
	
    public Consumer(List<Integer> bufferCondiviso, int size) {
        this.bufferCondiviso = bufferCondiviso;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Il thread Consumer sta leggendo il buffer... ");
                consume();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        // il thread resta in stato wait se il buffer è vuoto
    	
        while (bufferCondiviso.isEmpty()) {
            synchronized (bufferCondiviso) {
                System.out.println("Il buffer è vuoto, il thread Consumer resta in attesa... "
                		+ "la dimensione del buffer adesso è: " + bufferCondiviso.size());

                bufferCondiviso.wait();  // libero la risorsa (lock) in attesa di essere richiamata da Producer (in questo caso)
            }
        }

        // il buffer contiene elementi, quindi il thread può eliminarne uno e notificarlo al producer
        synchronized (bufferCondiviso) {
        	System.out.println("Il thread Consumer sta leggendo il buffer ed eliminando il seguente elemento: " 
        			+ bufferCondiviso.remove(0) + " la dimensione del buffer adesso è: " + bufferCondiviso.size());
        	
        	bufferCondiviso.notifyAll();	// risveglia il thread in stato lock di Producer
        }
    } 
}
