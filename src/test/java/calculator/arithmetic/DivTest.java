package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import com.epam.tat.module4.Calculator;
import org.junit.Test;

public class DivTest {

    //junit demo

    @Test
    public void divisionTest() {
        Calculator calculator = new Calculator();
        long actual = calculator.div(22, 2);
        long expected = 22 / 2;
        assertEquals(actual, expected, "invalid result of div operation");
    }
}
