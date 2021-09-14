package concorrenza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) {
		
		Main m = new Main();
		
		m.esempioThread();
		try {
			m.esempioConcorrenzaExec();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		m.esempioConcorrenzaForJoin();
	}
	
	private void esempioThread() {
		
		GetSitePage s1 = new GetSitePage("http://www.google.it");
		
		s1.start();
		try {
			s1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(s1.getContent());
	}
	
	private void esempioConcorrenzaExec() throws InterruptedException, ExecutionException {
		List<Callable<String>> siti = new ArrayList<Callable<String>>();
		
		siti.add(new GetSitePageExecutor("http://www.google.it"));
		
		ExecutorService ex = Executors.newSingleThreadExecutor();
		List<Future<String>> out = ex.invokeAll(siti);
		
		for (Future<String> future : out) {
			System.out.println(future.get());
		}
		ex.shutdown();
	}
	
	private void esempioConcorrenzaForJoin() {
		
		ForkJoinPool f = new ForkJoinPool();
		System.out.println(f.invoke(new GetSitePageForJoin("http://www.google.it")));
		f.shutdown();
	}
}
