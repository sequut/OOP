import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CreatePizza implements Runnable {
    private final BlockingQueue<Order> orderQueue;
    private final BlockingQueue<Order> storageQueue;
    private final int experience;

    CreatePizza(BlockingQueue<Order> orderQueue, BlockingQueue<Order> storageQueue, int experience){
        this.orderQueue = orderQueue;
        this.storageQueue = storageQueue;
        this.experience = experience;
    }

    private void cook(Order order) throws InterruptedException{
        TimeUnit.SECONDS.sleep((order.getKindPizza().getTime()) / experience);
        System.out.println("id: " + String.format("%2d", order.getOrderId()) + " cooked");
    }

    @Override
    public void run(){
        while (!orderQueue.isEmpty()){
            try {
                Order order = orderQueue.take();
                cook(order);
                System.out.println("id: "+ String.format("%2d",order.getOrderId()) + " in storage now");
                storageQueue.add(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
