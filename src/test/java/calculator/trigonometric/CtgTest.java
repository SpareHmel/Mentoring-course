package calculator.trigonometric;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.Test;

public class CtgTest extends CalcTestNGBase {

  @Test
  public void ctgTest() {
    double actual = calculator.ctg(8);
    double expected = 1 / Math.tan(8);
    assertEquals(actual, expected, "invalid result of ctg operation");
  }
}
