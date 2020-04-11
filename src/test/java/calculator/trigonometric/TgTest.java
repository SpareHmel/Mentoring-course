package calculator.trigonometric;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcTestNGBase;
import org.testng.annotations.Test;

public class TgTest extends CalcTestNGBase {

  @Test
  public void tgTest() {
    double actual = calculator.tg(15);
    double expected = Math.tan(15);
    assertEquals(actual, expected, "invalid result of tg operation");
  }
}
