package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SumTest extends CalcTestNGBase {

    @DataProvider
    public static Object[][] calcData() {
        return new Object[][]{{10, -22}, {55, 5}, {-6, -7}};
    }

    @Test(dataProvider = "calcData")
    public void sumTest(long summand1, long summand2) {
        long actual = calculator.sum(summand1, summand2);
        long expected = summand1 + summand2;
        assertEquals(actual, expected, "invalid result of sum operation");
    }
}
