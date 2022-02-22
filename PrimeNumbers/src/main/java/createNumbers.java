import java.util.ArrayList;
import static com.google.common.math.IntMath.isPrime;
public class createNumbers {
    public ArrayList<Integer> createRandom(int num){
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < num; i++)
            answer.add((int) (Math.random() * 100000));
        return answer;
    }

    public ArrayList<Integer> createPrime(int num){
        ArrayList<Integer> answer = new ArrayList<>();

        answer.add(2);
        int i = 3;
        while (answer.size() != num){
            if (isPrime(i))
                answer.add(i);
            i += 2;
        }
        return answer;
    }
}
