package calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CalculatorTest {

    @Test
    public void TestAll(){
        String[] test = new String[0];
        test = Arrays.copyOf(test, 9);
        test[0] = "+ 9 * 2 6";
        test[1] = "sin + - 1 2 1";
        test[2] = "cos * 10 - 1 1";
        test[3] = "log cos * 10 - 1 1";
        test[4] = "/ / 9 3 2";
        test[5] = "/ 1 3";
        test[6] = "pow pow 2 2 2";
        test[7] = "sqrt 144";

        test[8] = "pow sqrt pow 2 2 3";

        assertEquals(21, Calculator.calculateInReal(test[0]));
        assertEquals(0, Calculator.calculateInReal(test[1]));
        assertEquals(1, Calculator.calculateInReal(test[2]));
        assertEquals(0, Calculator.calculateInReal(test[3]));
        assertEquals(1.5, Calculator.calculateInReal(test[4]));
        assertEquals((double) 1/3, Calculator.calculateInReal(test[5]));
        assertEquals(16, Calculator.calculateInReal(test[6]));
        assertEquals(12, Calculator.calculateInReal(test[7]));
        assertEquals(8, Calculator.calculateInReal(test[8]));

        String[] finalTest = test;
        Throwable thrown_1 = assertThrows(RuntimeException.class,
                () -> Calculator.calculateInReal("+ 9 * 2 a"));
        assertEquals(thrown_1.getMessage(), "Invalid token: a");

        Throwable thrown_2 = assertThrows(RuntimeException.class,
                () -> Calculator.calculateInReal("+ 9 * 2 3 ^"));
        assertEquals(thrown_2.getMessage(), "Invalid token: ^");
    }
}