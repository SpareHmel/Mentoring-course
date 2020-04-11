package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.Test;

public class PowTest extends CalcTestNGBase {

  @Test
  public void powTest() {
    double actual = calculator.pow(2.2, 2.3);
    double expected = Math.pow(2.2, 2.3);
    assertEquals(actual, expected, "invalid result of pow operation");
  }
}
