package calculator.trigonometric;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcBase;
import org.testng.annotations.Test;

public class CosTest extends CalcBase {

  @Test
  public void cosTest() {
    double actual = calculator.cos(0);
    double expected = Math.cos(0);
    assertEquals(actual, expected);
  }
}
