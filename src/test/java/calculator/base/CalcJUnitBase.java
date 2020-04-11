package calculator.base;

import com.epam.tat.module4.Calculator;
import org.junit.After;
import org.junit.Before;

public class CalcJUnitBase {

    protected Calculator calculator;

    @Before
    public void before() {
        calculator = new Calculator();
    }

    @After
    public void after() {
        calculator = null;
    }
}
