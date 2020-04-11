package calculator.positive_or_negative;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IsNegativeTest extends CalcTestNGBase {

  @DataProvider
  public static Object[] calcData() {
    return new Object[]{1, -13, 0};
  }

  @Test(dataProvider = "calcData")
  public void isNegativeTest(long number) {
    boolean actual = calculator.isNegative(number);
    boolean expected = number < 0L;
    assertEquals(actual, expected, "invalid result of isNegative operation");
  }
}
