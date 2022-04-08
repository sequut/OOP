import java.util.ArrayList;
import java.util.Arrays;
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
        while(true){
            try {
                int currentSize = 0;
                int countRequest = 0;
                /*
                courier can make only 3 requests to storage
                also this feature can be disabled very simply
                 */
                ArrayList<Order> courier = new ArrayList<>();

                TimeUnit.SECONDS.sleep(2); //waiting for ready orders

                while (!storageQueue.isEmpty() && countRequest < 3){
                    Order order = storageQueue.peek();
                    countRequest += 1;
                    if (order != null && currentSize + order.getKindPizza().getSize() < size){
                        Order takeOrder = storageQueue.take();
                        courier.add(takeOrder);
                        currentSize += takeOrder.getKindPizza().getSize();
                    }
                    else
                        break;
                }
                if (courier.size() > 0){
                    System.out.println("COURIER TAKES TAKES PIZZA FOR DELIVERY");
                    delivery(courier);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("courier is done");
    }

    private void delivery(ArrayList<Order> courier) throws InterruptedException {
        ArrayList<Integer> ordersId = new ArrayList<>();
        for (Order order: courier) {
            ordersId.add(order.getOrderId());
            TimeUnit.SECONDS.sleep(order.getDeliveryTime());
            System.out.println("id: " + String.format("%2d",order.getOrderId()) + " || pizza: " + order.getKindPizza().getInfo() + " AT HOME NOW");
        }

        System.out.println("ORDERS: " + Arrays.toString(ordersId.toArray()));
    }
}
