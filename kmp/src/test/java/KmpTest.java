import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class KmpTest {
    @Test
    public void check_string_beginning_with_substring() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test1.txt", "test");

        Integer[] answer = new Integer[]{0};
        assertArrayEquals(answer, check);
    }

    @Test
    public void check_substring_with_substring() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test2.txt", "aabaab");
        Integer[] answer = new Integer[]{0, 10, 13, 16};
        assertArrayEquals(answer, check);

    }

    @Test
    public void check_string_ending_with_substring() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test3.txt", "test");

        Integer[] answer = new Integer[]{11};
        assertArrayEquals(answer, check);
    }

    @Test
    public void check_empty_file() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test4.txt", "test");

        assertArrayEquals(null, check);
    }

    @Test
    public void check_empty_file_and_answer() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test4.txt", "");

        assertArrayEquals(null, check);
    }

    @Test
    public void check_empty_answer() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test5.txt", "");

        assertArrayEquals(null, check);
    }

    @Test
    public void check_with_big_test() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test6.txt", "abv");

        Integer[] answer = new Integer[]{14400000};
        assertArrayEquals(answer, check);
    }
}