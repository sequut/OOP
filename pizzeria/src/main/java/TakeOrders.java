import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TakeOrders implements Runnable {
    private final BlockingQueue<Order> orderQueue;
    private final Random random;
    private final RandomOrders randomOrders = new RandomOrders();

    TakeOrders(BlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
        random = new Random();
    }

    @Override
    public void run() {
        System.out.println("Pizzeria is open");
        while(true){
            try {
                TimeUnit.SECONDS.sleep(3 + random.nextInt() % 7);
                Order order = randomOrders.getOrder();
                orderQueue.add(order);
                System.out.println("id: " + String.format("%2d", order.getOrderId()) + "| pizza: " + order.getKindPizza().getInfo() + "| order in queue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
