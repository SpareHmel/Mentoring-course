package calc;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SubtractionTest extends Base {

    @DataProvider
    public static Object[][] calcData() {
        return new Object[][] {{90, 115, -25}, {5, 5, 0}, {3, -5, 8}};
    }

    @Test(dataProvider = "calcData")
    public void subtractionTest(long minuend, long subtrahend, long expected) {
        long actual = calculator.sub(minuend, subtrahend);
        assertEquals(actual, expected);
    }
}
