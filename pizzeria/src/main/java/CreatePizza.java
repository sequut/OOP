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
        System.out.println("id: " + String.format("%2d", order.getOrderId()) + " || pizza: " + order.getKindPizza().getInfo() + " COOKED");
    }

    @Override
    public void run(){
        while (true){
            try {
                Order order = orderQueue.take();
                cook(order);
                System.out.println("id: " + String.format("%2d",order.getOrderId()) + " || pizza: " + order.getKindPizza().getInfo() + " IN STORAGE NOW");
                storageQueue.add(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}   
