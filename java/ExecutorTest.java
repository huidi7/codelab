import java.util.concurrent.*;

public class ExecutorTest {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(()->{
      try {
        TimeUnit.SECONDS.sleep(10);
        System.out.println("callable entry");
      } catch (InterruptedException e) {
        System.out.println("Get exception in callable");
        e.printStackTrace();
      }
    });

    try {
      System.out.println("shutdown.");
      executor.shutdown();
      executor.awaitTermination(4, TimeUnit.SECONDS);
    } catch(InterruptedException e) {
      e.printStackTrace();
    } finally {
      if (!executor.isTerminated()) {
        System.out.println("shutdown forcely.");
        executor.shutdownNow();
      }
    }
  }
}
