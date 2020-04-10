package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class SinTest extends Base {

  @Test
  public void sinTest() {
    double actual = calculator.sin(0);
    assertEquals(actual, 0.0);
  }
}
