import java.util.Random;

public class RandomOrders {
    private final Random random;

    RandomOrders(){
        random = new Random();
    }

    public Order getOrder(){
        return new Order(new KindPizza(KindPizza.Flavour.values()[Math.abs(random.nextInt() % KindPizza.Flavour.values().length)],
                                                KindPizza.Size.values()[Math.abs(random.nextInt() % KindPizza.Size.values().length)]),
                                            random.nextInt() % 3);
    }
}