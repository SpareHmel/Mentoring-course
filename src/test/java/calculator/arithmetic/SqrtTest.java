package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.Test;

public class SqrtTest extends CalcTestNGBase {

  @Test
  public void sqrtTest() {
    double actual = calculator.sqrt(13.5);
    double expected = Math.sqrt(13.5);
    assertEquals(actual, expected, "invalid result of sqrt operation");
  }
}
