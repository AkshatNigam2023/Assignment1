import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Customer implements Runnable {
    private final LinkedBlockingQueue<Order> orderQueue;
    private final Random rand = new Random();
    public Customer(LinkedBlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }
    @Override
    public void run() {
        while(true){
            try {
                // Random cargo between 10 and 50 tonnes
                Order order = new Order(rand.nextInt(51), rand.nextBoolean()?"Gotham":"Atlanta");
                orderQueue.put(order);

                // time between new orders somewhere around 5 seconds is optimal
                TimeUnit.SECONDS.sleep(5); // Adjust the wait time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
