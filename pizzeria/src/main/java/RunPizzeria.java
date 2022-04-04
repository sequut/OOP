import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class RunPizzeria {
    private final PizzeriaData pizzeriaData;
    private final LinkedBlockingQueue<Order> orderQueue;
    private final ArrayBlockingQueue<Order> storageQueue;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    RunPizzeria(PizzeriaData pizzeriaData){
        this.pizzeriaData = pizzeriaData;
        orderQueue = new LinkedBlockingQueue<>();
        storageQueue = new ArrayBlockingQueue<>(pizzeriaData.getStorage());

        pizzeriaData.writeAllData();

        openPizzeria();
        working();

        executor.shutdown();
    }

    private void openPizzeria(){
        executor.execute(new TakeOrders(orderQueue));
    }

    private void working(){
        for (int i = 0; i < pizzeriaData.getBackersExp().length; i++)
            executor.execute(new CreatePizza(orderQueue, storageQueue, pizzeriaData.getBackersExp()[i]));

        int[] deli = pizzeriaData.getDelivery();
        for (int j : deli) executor.execute(new Delivery(storageQueue, j));
    }
}
