package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubTest extends CalcTestNGBase {

    @DataProvider
    public static Object[][] calcData() {
        return new Object[][]{{90, 115}, {5, 5}, {3, -5}};
    }

    @Test(dataProvider = "calcData")
    public void subtractionTest(long minuend, long subtrahend) {
        long actual = calculator.sub(minuend, subtrahend);
        long expected = minuend - subtrahend;
        assertEquals(actual, expected, "invalid result of sub operation");
    }
}
