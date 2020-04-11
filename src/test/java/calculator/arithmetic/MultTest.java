package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MultTest extends CalcTestNGBase {

  @DataProvider
  public static Object[][] calcData() {
    return new Object[][]{{8, -8}, {3, 7}, {-6, -7}};
  }

  @Test(dataProvider = "calcData")
  public void multiplicationTest(long multiplicand, long factor) {
    long actual = calculator.mult(multiplicand, factor);
    long expected = multiplicand * factor;
    assertEquals(actual, expected, "invalid result of mult operation");
  }
}
