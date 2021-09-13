import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.net.Inet4Address;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Heapsorttest {

    @Test
    public void test_1() {
        int[] input = new int[]{5, 4, 3, 2, 1};
        int[] answer = new int[]{1, 2, 3, 4, 5};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void test_2() {
        int[] input = new int[]{-10, 4, 7, 3, -2, 0};
        int[] answer = new int[]{-10, -2, 0, 3, 4, 7};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void test_3() {
        int[] input = new int[]{-1, -5, 0, 1, -2, -3};
        int[] answer = new int[]{-5, -3, -2, -1, 0, 1};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void test_4() {
        int[] input = new int[]{-10, 150, 1, 6, -123, 32, 3};
        int[] answer = new int[]{-123, -10, 1, 3, 6, 32, 150};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void test_5() {
        int[] input = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 6, -123, 32, 3};
        int[] answer = new int[]{Integer.MIN_VALUE, -123, 1, 3, 6, 32, Integer.MAX_VALUE};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }


}
