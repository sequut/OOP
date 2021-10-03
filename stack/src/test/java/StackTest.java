import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;

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

        test.pop();
        assertEquals(4, test.count());

        test.popStack(3);
        assertEquals(1, test.count());

        test.write();

        test.pop();
        assertEquals(0, test.count());

        test.pop();
        assertEquals(0, test.count());

        test.popStack(2);
        assertEquals(0, test.count());
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

        test.pop();
        assertEquals(4, test.count());

        test.popStack(3);
        assertEquals(1, test.count());

        test.write();

        test.pop();
        assertEquals(0, test.count());

        test.pop();
        assertEquals(0, test.count());

        test.popStack(2);
        assertEquals(0, test.count());
    }
}
