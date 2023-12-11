import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Ship implements Runnable {
    private final LinkedBlockingQueue<Order> orderQueue;
    private int completedTrips;
    private static int totalRevenue;
    private int consecutiveCanceledOrders;
    private static final int maxCargo = 300;
    private static final int minCargo = 50;
    public Ship(LinkedBlockingQueue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }
    @Override
    public void run() {
        try {
            while(totalRevenue < 1000000) {
                Order order = orderQueue.poll(1, TimeUnit.MINUTES);
                if(order == null) {
                    consecutiveCanceledOrders++;
                    if(consecutiveCanceledOrders == 3) {
                        // Customer leaves if 3 orders are canceled continuously
                        System.out.println("Customer leaving due to continuous cancellations");
                        break;
                    }
                }
                else {
                    int cargoWeight = Math.max(Math.min(order.getCargoWeight(), maxCargo), minCargo);

                    if(order.getDestination().equals("Gotham")) {
                        // Calculating revenue
                        totalRevenue += cargoWeight * 1000;

                        // Simulate ship going back
                        orderQueue.put(new Order(cargoWeight, "Atlanta"));
                        completedTrips++;
                    } else {
                        // Process the return order
                        orderQueue.put(new Order(cargoWeight, "Gotham"));
                    }
                    consecutiveCanceledOrders = 0; // Reset consecutive canceled orders
                }
                System.out.println("Overall Revenue = "+totalRevenue);

                //Maintenance
                if (completedTrips % 5 == 0) {
                    System.out.println("Maintenance Time...");
                    TimeUnit.SECONDS.sleep(6);
                }
            }
            System.out.println("Reached target.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}