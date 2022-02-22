import static com.google.common.math.IntMath.isPrime;
import java.util.ArrayList;

public class wParallelStream {
    private ArrayList<Integer> numbers;

    wParallelStream(ArrayList<Integer> arrayList){
        this.numbers = arrayList;
    }

    public boolean count(){
        boolean answer = numbers.parallelStream().anyMatch(x -> !isPrime(x));
        return answer;
    }
}