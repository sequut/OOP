import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class RunPizzeria {
    private final PizzeriaData pizzeriaData;
    private final LinkedBlockingQueue<Order> orderQueue;
    private final ArrayBlockingQueue<Order> storageQueue;

    private final ExecutorService executor;

    RunPizzeria(PizzeriaData pizzeriaData){
        this.executor = Executors.newCachedThreadPool();
        this.pizzeriaData = pizzeriaData;
        this.orderQueue = new LinkedBlockingQueue<>();
        this.storageQueue = new ArrayBlockingQueue<>(pizzeriaData.getStorage());

        pizzeriaData.writeAllData();

        openPizzeria();
        working();

        executor.shutdown();
    }

    private void openPizzeria(){
        executor.execute(new TakeOrders(orderQueue));
    }

    private void working(){
        for (int exp : pizzeriaData.getBackersExp())
            executor.execute(new CreatePizza(orderQueue, storageQueue, exp));

        for (int deliSize : pizzeriaData.getDelivery())
            executor.execute(new Delivery(storageQueue, deliSize));
    }
}
