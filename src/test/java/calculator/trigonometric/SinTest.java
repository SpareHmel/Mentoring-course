package calculator.trigonometric;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcBase;
import org.testng.annotations.Test;

public class SinTest extends CalcBase {

  @Test
  public void sinTest() {
    double actual = calculator.sin(0);
    double expected = Math.sin(0);
    assertEquals(actual, expected, "invalid result of sin operation");
  }
}
