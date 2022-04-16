import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;


public class TestCouriers {
    @Test
    public void DeliverPizza() throws InterruptedException {
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        ArrayBlockingQueue<Order> storageQueue = new ArrayBlockingQueue<>(2);
        RandomOrders randomOrders = new RandomOrders();

        orderQueue.add(randomOrders.getOrder());
        orderQueue.add(randomOrders.getOrder());

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new CreatePizza(orderQueue, storageQueue, 2));
        executor.execute(new CreatePizza(orderQueue, storageQueue, 3));

        executor.execute(new Delivery(storageQueue, 3));
        executor.execute(new Delivery(storageQueue, 3));

        TimeUnit.SECONDS.sleep(20);

        Assertions.assertTrue(storageQueue.isEmpty());

        executor.shutdown();
    }


    @Test
    public void NoPizza() throws InterruptedException {
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        ArrayBlockingQueue<Order> storageQueue = new ArrayBlockingQueue<>(2);
        RandomOrders randomOrders = new RandomOrders();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new CreatePizza(orderQueue, storageQueue, 2));
        executor.execute(new CreatePizza(orderQueue, storageQueue, 3));

        executor.execute(new Delivery(storageQueue, 3));
        executor.execute(new Delivery(storageQueue, 3));

        TimeUnit.SECONDS.sleep(2);

        Assertions.assertTrue(storageQueue.isEmpty());

        executor.shutdown();
    }
}
