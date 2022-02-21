import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        createNumbers make = new createNumbers();

        ArrayList<Integer> check = make.createRandom(100);
        wSeries series = new wSeries(check);
        long start = System.currentTimeMillis();
        System.out.println(series.count());
        long time = System.currentTimeMillis() - start;

        System.out.println("running time not parallel: " + time + "ms");

        wParallelStream parallelStream = new wParallelStream(check);
        start = System.currentTimeMillis();
        System.out.println(parallelStream.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with parallel stream: " + time + "ms");


        wThreads threads2 = new wThreads(check, 2);
        start = System.currentTimeMillis();
        System.out.println(threads2.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 2 threads: " + time + "ms");

        wThreads threads3 = new wThreads(check, 3);
        start = System.currentTimeMillis();
        System.out.println(threads3.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 3 threads: " + time + "ms");

        wThreads threads4 = new wThreads(check, 4);
        start = System.currentTimeMillis();
        System.out.println(threads4.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 4 threads: " + time + "ms");

        wThreads threads5 = new wThreads(check, 5);
        start = System.currentTimeMillis();
        System.out.println(threads5.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 5 threads: " + time + "ms");

        wThreads threads6 = new wThreads(check, 5);
        start = System.currentTimeMillis();
        System.out.println(threads6.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 6 threads: " + time + "ms");

        wThreads threads7 = new wThreads(check, 5);
        start = System.currentTimeMillis();
        System.out.println(threads7.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 7 threads: " + time + "ms");

        wThreads threads8 = new wThreads(check, 5);
        start = System.currentTimeMillis();
        System.out.println(threads8.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 8 threads: " + time + "ms");

        wThreads threads9 = new wThreads(check, 5);
        start = System.currentTimeMillis();
        System.out.println(threads9.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 9 threads: " + time + "ms");

        wThreads threads10 = new wThreads(check, 5);
        start = System.currentTimeMillis();
        System.out.println(threads10.count());
        time = System.currentTimeMillis() - start;
        System.out.println("running time with 10 threads: " + time + "ms");
    }
}