import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackTest {

    @Test
    public void testInt(){
        Stack<Integer> test = new Stack<>();

        test.push(1);
        assertEquals(1, test.count());

        test.push(345);
        assertEquals(2, test.count());

        Integer[] help = {6, 99, 100};
        test.pushStack(help);
        assertEquals(5, test.count());

        test.write();

        Integer ret;
        ret  = (Integer) test.pop();
        assertEquals(100, ret);

        Integer[] testing;

        testing = (Integer[]) test.popStack(1);
        assertEquals(99, testing[0]);

        test.write();

        ret  = (Integer) test.pop();
        assertEquals(1, ret);

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, test::pop);
        assertEquals(thrown.getMessage(), "stack is empty");

        assertEquals(0, test.count());

        Throwable thrown_1 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_1.getMessage(), "there are not so many elements on the stack");

        test.push(12);
        assertEquals(1, test.count());

        Throwable thrown_2 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_2.getMessage(), "there are not so many elements on the stack");

        assertEquals(1, test.count());
    }

    @Test
    public void testStr(){
        Stack<String> test = new Stack<>();

        test.push("aa");
        assertEquals(1, test.count());

        test.push("bb");
        assertEquals(2, test.count());

        String[] help = {"cc", "dd", "ee"};
        test.pushStack(help);
        assertEquals(5, test.count());

        test.write();

        String ret;
        ret  = (String) test.pop();
        assertEquals("ee", ret);

        test.popStack(3);
        assertEquals(1, test.count());

        test.write();

        ret  = (String) test.pop();
        assertEquals("aa", ret);

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, test::pop);
        assertEquals(thrown.getMessage(), "stack is empty");

        assertEquals(0, test.count());

        Throwable thrown_1 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_1.getMessage(), "there are not so many elements on the stack");

        test.push("testing");
        assertEquals(1, test.count());

        Throwable thrown_2 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_2.getMessage(), "there are not so many elements on the stack");

        assertEquals(1, test.count());
    }
}