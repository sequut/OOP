package calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.CompactNumberFormat;
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

    @Test
    public void TestComplex(){
        Complex c1 = new Complex(12, -5);
        Complex c2 = new Complex(12, 5);
        Complex c3 = new Complex(0, -5);
        Complex c4 = new Complex(12);

        Complex help1 = c1.getComplex();
        Complex help2;

        Complex answ1 = new Complex(0.7041420118343196, -0.7100591715976331);
        Complex answ2 = new Complex();
        Complex answ3 = new Complex(119, -120);
        Complex answ4 = new Complex(24);
        Complex answ5 = new Complex();
        Complex answ6 = new Complex(12, 5);
        Complex answ7 = new Complex();
        Complex answ8 = new Complex(-25);


        help1.divide(c2);
        assertEquals(answ1.makeString(), help1.makeString());

        help1 = c1.getComplex();
        help1.sub(help1);
        assertEquals(answ2.makeString(), help1.makeString());

        help1 = c1.getComplex();
        help1.multiply(help1);
        assertEquals(answ3.makeString(), help1.makeString());

        help1 = c1.getComplex();
        help1.add(c2);
        assertEquals(answ4.makeString(), help1.makeString());

        help1 = c3.getComplex();
        help1.add(help1.getConjugate());
        assertEquals(answ5.makeString(), help1.makeString());

        help1 = c4.getComplex();
        help2 = c3.getComplex();
        help1.sub(help2);
        assertEquals(answ6.makeString(), help1.makeString());

        help1 = c4.getComplex();
        help1.sub(help1);
        assertEquals(answ7.makeString(), help1.makeString());

        help1 = c3.getComplex();
        help1.multiply(help1);
        assertEquals(answ8.makeString(), help1.makeString());
    }
}