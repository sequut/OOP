import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        createNumbers make = new createNumbers();

        ArrayList<Integer> check = make.create(100000000);
        wSeries series = new wSeries(check);
        long start = System.currentTimeMillis();
        System.out.println(series.count());
        long time = System.currentTimeMillis() - start;

        System.out.println("running time: " + time + "ms");
    }
}