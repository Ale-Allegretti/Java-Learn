package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EsempioThreadPool {
	public static void main(String[ ] args) {
		/* creo il thread pool */
		ExecutorService pool = Executors.newCachedThreadPool();

		/* aggiunge i thread al pool */
		pool.execute(new GetSitePage("http://www.paolopreite.it"));
		pool.execute(new GetSitePage("http://www.google.it"));
		pool.execute(new GetSitePage("http://www.amazon.com"));
		
		pool.shutdown();
		
//		ExecutorService threadpool = Executors.newCachedThreadPool();
//
//		/* metto in coda i thread */
//		threadpool.execute(new GetSitePage("http://www.paolopreite.it"));
//		threadpool.execute(new GetSitePage("https://www.google.it"));
//		
//		/* spengo il thread pool */
//		threadpool.shutdown();
		
		/*
		 * se provate ad aggiungere un nuovo thread dopo aver invocato il metodo shutdown ci sarà la seguente eccezione
		 * 
		 * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task Thread[Thread-2,5,main] rejected from java.util.concurrent.ThreadPoolExecutor@5c647e05[Shutting down, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2047)
	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:823)
	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1369)
	at it.preite.pool.EsempioThreadPool.main(EsempioThreadPool.java:18)
	
		threadpool.execute(new GetSitePage("https://www.google.it"));
		*/
	}
}
