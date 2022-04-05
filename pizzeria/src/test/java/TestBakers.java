import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class TestBakers {

    @Test
    public void MakePizzaTest() throws InterruptedException {
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        ArrayBlockingQueue<Order> storageQueue = new ArrayBlockingQueue<>(2);
        RandomOrders randomOrders = new RandomOrders();

        orderQueue.add(randomOrders.getOrder());
        orderQueue.add(randomOrders.getOrder());

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new CreatePizza(orderQueue, storageQueue, 2));
        executor.execute(new CreatePizza(orderQueue, storageQueue, 3));

        TimeUnit.SECONDS.sleep(2);

        Assertions.assertTrue(orderQueue.isEmpty());

        executor.shutdown();
    }


    @Test
    public void NoPizzaTest() throws InterruptedException {
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        ArrayBlockingQueue<Order> storageQueue = new ArrayBlockingQueue<>(2);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new CreatePizza(orderQueue, storageQueue, 2));
        executor.execute(new CreatePizza(orderQueue, storageQueue, 3));

        TimeUnit.SECONDS.sleep(1);

        Assertions.assertTrue(orderQueue.isEmpty());

        executor.shutdown();
    }
}
