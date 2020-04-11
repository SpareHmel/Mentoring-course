package calculator.arithmetic;

import calculator.base.CalcBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SumTest extends CalcBase {

    @DataProvider
    public static Object[][] calcData() {
        return new Object[][] {{10, -22}, {55, 5}, {-6, -7}};
    }

    @Test(dataProvider = "calcData")
    public void sumTest(long summand1, long summand2) {
        long actual = calculator.sum(summand1, summand2);
        long expected = summand1 + summand2;
        assertEquals(actual, expected);
    }
}
