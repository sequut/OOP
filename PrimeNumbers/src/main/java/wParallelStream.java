import static com.google.common.math.IntMath.isPrime;
import java.util.ArrayList;

public class wParallelStream {
    private ArrayList<Integer> numbers;

    wParallelStream(ArrayList<Integer> arrayList){
        this.numbers = arrayList;
    }

    public boolean count(){
        int calc = (int) numbers.parallelStream().filter(x -> isPrime(x)).count();
        return calc != numbers.size();
    }
}