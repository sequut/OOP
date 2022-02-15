import static com.google.common.math.IntMath.isPrime;
import java.util.ArrayList;

public class wThreads {
    private ArrayList<Integer> numbers;
    private boolean answer;

    wThreads(ArrayList<Integer> arrayList){
        this.numbers = arrayList;
    }

    public boolean count(){
        return answer;
    }
}