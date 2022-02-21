import java.util.ArrayList;
import java.util.List;

import static com.google.common.math.IntMath.isPrime;

public class wThreads {
    private ArrayList<Integer> numbers;
    private int threads_number;
    private Integer final_answer = 0;

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
        Threads(List<Integer> count){
            this.count = count;
        }

        @Override
        public void run(){
            for (Integer value: numbers)
                if (isPrime(value))
                    final_answer += 1;
        }
    }


    public boolean count(){
        Threads[] threads = new Threads[threads_number];
        for (int i = 0; i < threads_number; i++){
            threads[i] = new Threads(numbers.subList(i * numbers.size() / threads_number,
                    (i + 1) * numbers.size() / threads_number));
            threads[i].start();
        }
        for (int i = 0; i < threads_number; i++){
            try{
                threads[i].join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        return final_answer != numbers.size();
    }
}