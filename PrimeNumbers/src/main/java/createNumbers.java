import java.util.ArrayList;

public class createNumbers {
    public ArrayList<Integer> createRandom(int num){
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < num; i++)
            answer.add((int) (Math.random() * 100000));
        return answer;
    }
}
