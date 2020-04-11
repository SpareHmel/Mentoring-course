package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcBase;
import org.testng.annotations.Test;

public class SqrtTest extends CalcBase {

  @Test
  public void sqrtTest() {
    double actual = calculator.sqrt(13.5);
    double expected = Math.sqrt(13.8);
    assertEquals(actual, expected,"invalid result of sqrt operation");
  }
}
