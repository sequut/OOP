import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TakeOrders implements Runnable {
    private final BlockingQueue<Order> orderQueue;
    private final Random random;
    private final RandomOrders randomOrders;

    TakeOrders(BlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
        this.random = new Random();
        this.randomOrders = new RandomOrders();
    }

    @Override
    public void run() {
        System.out.println("----------------------");
        System.out.println("|| Pizzeria is open ||");
        System.out.println("----------------------\n");

        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1 + random.nextInt() % 4);
                Order order = randomOrders.getOrder();
                orderQueue.add(order);
                System.out.println("id: " + String.format("%2d", order.getOrderId()) + " || pizza: " + order.getKindPizza().getInfo() + " IN QUEUE");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
