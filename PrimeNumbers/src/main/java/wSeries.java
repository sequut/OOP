import static com.google.common.math.IntMath.isPrime;
import java.util.ArrayList;

public class wSeries {
    private final ArrayList<Integer> numbers;
    private boolean answer;

    wSeries(ArrayList<Integer> arrayList){
        this.numbers = arrayList;
    }

    public boolean count(){
        for (Integer value: numbers)
            if (!isPrime(value))
                answer = true;
        return answer;
    }
}