package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TgTest extends Base {

  @Test()
  public void tgTest() {
    double actual = calculator.tg(15);
    assertEquals(actual, 1.0);
  }
}
