package calc;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SumTest extends Base {

    @DataProvider
    public static Object[][] calcData() {
        return new Object[][] {{10, -22, -12}, {55, 5, 60}, {-6, -7, -13}};
    }

    @Test(dataProvider = "calcData")
    public void sumTest(long summand1, long summand2, long expected) {
        long actual = calculator.sum(summand1, summand2);
        assertEquals(actual, expected);
    }
}
