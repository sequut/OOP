import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class wParallelStreamTest {

    @Test
    public void TestOnlyPrimeNumbers(){
        ArrayList<Integer> check = new ArrayList(Arrays.asList(3, 5, 7, 13, 17));
        wParallelStream parallelStream = new wParallelStream(check);
        Assertions.assertFalse(parallelStream.count());
    }

    @Test
    public void TestMixedNumbers(){
        ArrayList<Integer> check = new ArrayList(Arrays.asList(3, 6, 7, 8, 17));
        wParallelStream parallelStream = new wParallelStream(check);
        Assertions.assertTrue(parallelStream.count());
    }

    @Test
    public void TestNotPrimeNumbers(){
        ArrayList<Integer> check = new ArrayList(Arrays.asList(4, 6, 8, 10, 15));
        wParallelStream parallelStream = new wParallelStream(check);
        Assertions.assertTrue(parallelStream.count());
    }
}