import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class wThreadsTest {
    @Test
    public void TestOnlyPrimeNumbers(){
        ArrayList<Integer> check = new ArrayList(Arrays.asList(3, 5, 7, 13, 17));
        wThreads threads = new wThreads(check);
        Assertions.assertFalse(threads.count());
    }

    @Test
    public void TestMixedNumbers(){
        ArrayList<Integer> check = new ArrayList(Arrays.asList(3, 6, 7, 8, 17));
        wThreads threads = new wThreads(check);
        Assertions.assertTrue(threads.count());
    }

    @Test
    public void TestNotPrimeNumbers(){
        ArrayList<Integer> check = new ArrayList(Arrays.asList(4, 6, 8, 10, 15));
        wThreads threads = new wThreads(check);
        Assertions.assertTrue(threads.count());
    }
}
