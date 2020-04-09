package calc;

import static org.testng.Assert.assertEquals;

import com.epam.tat.module4.Calculator;
import org.junit.Test;

public class DivisionTest {

    //junit demo
    Calculator calculator;

    @Test()
    public void divisionTest() {
        calculator = new Calculator();
        long actual = calculator.div(22, 2);
        assertEquals(actual, 11);
        calculator = null;
    }
}
