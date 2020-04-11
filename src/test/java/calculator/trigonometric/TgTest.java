package calculator.trigonometric;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcBase;
import org.testng.annotations.Test;

public class TgTest extends CalcBase {

  @Test
  public void tgTest() {
    double actual = calculator.tg(15);
    double expected = Math.tan(15);
    assertEquals(actual, expected);
  }
}
