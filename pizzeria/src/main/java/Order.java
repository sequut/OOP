public class Order {
    private static int current = 0;

    private final int orderId = ++current;
    private final int deliveryTime;
    private final KindPizza kindPizza;

    public Order(KindPizza kindPizza, int time){
        this.deliveryTime = time;
        this.kindPizza = kindPizza;
    }

    public int getOrderId(){
        return orderId;
    }

    public KindPizza getKindPizza(){
        return kindPizza;
    }

    public int getDeliveryTime(){
        return deliveryTime;
    }
}
