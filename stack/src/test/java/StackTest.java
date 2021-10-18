import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StackTest {

    @Test
    public void testInt(){
        SStack<Integer> test = new SStack<>();

        test.push(1);
        assertEquals(1, test.count());

        test.push(345);
        assertEquals(2, test.count());

        SStack<Integer> help = new SStack<>();
        help.push(6);
        help.push(99);
        help.push(100);
        test.pushStack(help);
        assertEquals(5, test.count());

        test.write();

        Integer ret;
        ret  = test.pop();
        assertEquals(100, ret);

        SStack<Integer> testing;
        testing = test.popStack(3);
        assertEquals(3, testing.count());

        ret = testing.pop();
        assertEquals(345, ret);

        test.write();

        ret = test.pop();
        assertEquals(1, ret);

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, test::pop);
        assertEquals(thrown.getMessage(), "stack is empty");

        assertEquals(0, test.count());

        Throwable thrown_1 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_1.getMessage(), "stack is empty");

        test.push(12);
        assertEquals(1, test.count());

        Throwable thrown_2 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_2.getMessage(), "there are not so many elements on the stack");

        assertEquals(1, test.count());
    }

    @Test
    public void testStr(){
        SStack<String> test = new SStack<>();

        test.push("aa");
        assertEquals(1, test.count());

        test.push("bb");
        assertEquals(2, test.count());

        SStack<String> help = new SStack<>();
        help.push("cc");
        help.push("dd");
        help.push("ee");
        test.pushStack(help);
        assertEquals(5, test.count());

        test.write();

        String ret;
        ret  = test.pop();
        assertEquals("ee", ret);

        SStack<String> testing;
        testing = test.popStack(3);
        assertEquals(3, testing.count());

        ret = testing.pop();
        assertEquals("bb", ret);

        test.write();

        ret  = test.pop();
        assertEquals("aa", ret);

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, test::pop);
        assertEquals(thrown.getMessage(), "stack is empty");

        assertEquals(0, test.count());

        Throwable thrown_1 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_1.getMessage(), "stack is empty");

        test.push("Stack");
        assertEquals(1, test.count());

        Throwable thrown_2 = assertThrows(IndexOutOfBoundsException.class, () -> test.popStack(2));
        assertEquals(thrown_2.getMessage(), "there are not so many elements on the stack");

        assertEquals(1, test.count());
    }
}