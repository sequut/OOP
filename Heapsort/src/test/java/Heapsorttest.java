import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.net.Inet4Address;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Heapsorttest {

    @Test
    public void check_positive_num() {
        int[] input = new int[]{5, 4, 3, 2, 1};
        int[] answer = new int[]{1, 2, 3, 4, 5};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void check_negative_positive_num() {
        int[] input = new int[]{-10, 4, 7, 3, -2, 0};
        int[] answer = new int[]{-10, -2, 0, 3, 4, 7};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void check_empty_arr() {
        int[] input = new int[]{};
        int[] answer = new int[]{};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }

    @Test
    public void check_max_min_int() {
        int[] input = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 6, -123, 32, 3};
        int[] answer = new int[]{Integer.MIN_VALUE, -123, 1, 3, 6, 32, Integer.MAX_VALUE};
        Heapsort.sort(input);
        assertArrayEquals(answer, input);
    }


}
