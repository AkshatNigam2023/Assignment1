import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class WayneEnterprisesSimulation {

    public static void main(String[] args) {
        LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(12); // 7 customers + 5 ships

        // Create customer threads
        int i=0;
        for(;i<7;i++) {
            executorService.submit(new Customer(orderQueue));
        }

        // Create ship threads
        for(;i<12;i++) {
            executorService.submit(new Ship(orderQueue));
        }
        executorService.shutdown();
    }
}