import java.util.ArrayList;
import java.util.List;

import static com.google.common.math.IntMath.isPrime;

public class wThreads {
    private ArrayList<Integer> numbers;
    private int threads_number;

    wThreads(ArrayList<Integer> arrayList){
        this.numbers = arrayList;
        this.threads_number = 4;
    }

    wThreads(ArrayList<Integer> arrayList, int numberOfThreads){
        this.numbers = arrayList;
        this.threads_number = numberOfThreads;
    }

    public class Threads extends Thread {
        private List<Integer> count;
        private boolean answ = false;
        Threads(List<Integer> count){
            this.count = count;
        }

        @Override
        public void run(){
            for (Integer value: count)
                if (!isPrime(value)){
                    answ = true;
                    return;
                }
        }
    }


    public boolean count(){
        Threads[] threads = new Threads[threads_number];
        for (int i = 0; i < threads_number; i++){
            threads[i] = new Threads(numbers.subList(i * numbers.size() / threads_number,
                    (i + 1) * numbers.size() / threads_number));
            threads[i].start();
        }

        int done = 0;
        boolean answer;
        int i = 0;
        while (true){
            if (threads[i].answ)
                return true;

            if (threads[i].getState().equals(Thread.State.TERMINATED))
                done += 1;

            if (done == threads_number)
                break;

            i += 1;
            i /= threads_number;
        }


        for (i = 0; i < threads_number; i++){
            if (threads[i].answ){
                return true;
            }
        }
        return false;
    }
}