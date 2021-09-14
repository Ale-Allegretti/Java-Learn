package thread.prodcons;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerTest {
	public static void main(String args[]) {
        List<Integer> bufferCondiviso = new LinkedList<Integer>();
        int size = 4;
        
        Thread prodThread = new Thread(new Producer(bufferCondiviso, size), "Producer");
        Thread consThread = new Thread(new Consumer(bufferCondiviso, size), "Consumer");
        
        
        prodThread.start();
        consThread.start();
    }
}
