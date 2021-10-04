import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class KmpTest {
    @Test
    public void check_substring_with_substring() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test1.txt", "aabaab");
        Integer[] answer = new Integer[]{0, 10, 13, 16};
        assertArrayEquals(answer, check);

    }

    @Test
    public void check_empty_file() throws IOException {
        Kmp aa = new Kmp();

        Integer[] check = aa.kmp("test2.txt", "test");
        Integer[] help = new Integer[]{};
        assertArrayEquals(help, check);
    }

    @Test
    public void check_with_big_test() throws IOException {
        Kmp aa = new Kmp();
        Integer[] check = aa.kmp("test3.txt", "abv");

        Integer[] answer = new Integer[]{14400000};
        assertArrayEquals(answer, check);
    }

    @Test
    public void check_with_no_existing_file(){
        Kmp aa = new Kmp();
        Throwable thrown = assertThrows(IOException.class, () -> {
            Integer[] check = aa.kmp("test666.txt", "abv");
        });
        assertEquals(thrown.getMessage(), "no such file");
    }


    @Test
    public void check_with_no_existing_substring(){
        Kmp aa = new Kmp();
        Throwable thrown = assertThrows(IOException.class, () -> {
            Integer[] check = aa.kmp("test2.txt", "");
        });
        assertEquals(thrown.getMessage(), "substring is null");
    }
}