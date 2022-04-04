import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Delivery implements Runnable {
    private final BlockingQueue<Order> storageQueue;
    private final int size;

    Delivery(BlockingQueue<Order> storageQueue, int size){
        this.storageQueue = storageQueue;
        this.size = size;
    }

    @Override
    public void run() {
        int currentSize = 0;
        while(!storageQueue.isEmpty()){
            try {
                ArrayList<Order> courier = new ArrayList<>();
                while (!storageQueue.isEmpty()){
                    Order order = storageQueue.peek();
                    if (currentSize + order.getKindPizza().getSize() < size){
                        Order takeOrder = storageQueue.take();
                        courier.add(takeOrder);
                        currentSize += takeOrder.getKindPizza().getSize();
                    }
                    else
                        break;
                }
                if (courier.size() > 0){
                    System.out.println("courier takes pizza for delivery");
                    delivery(courier);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("courier is done");
    }

    private void delivery(ArrayList<Order> courier) throws InterruptedException {
        for (Order order: courier) {
            TimeUnit.SECONDS.sleep(order.getDeliveryTime());
            System.out.println(order.getKindPizza().getInfo() + " at home");
        }
    }
}
