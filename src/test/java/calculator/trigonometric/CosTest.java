package calculator.trigonometric;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.Test;

public class CosTest extends CalcTestNGBase {

  @Test
  public void cosTest() {
    double actual = calculator.cos(0);
    double expected = Math.cos(0);
    assertEquals(actual, expected, "invalid result of cos operation");
  }
}
