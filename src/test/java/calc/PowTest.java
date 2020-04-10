package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class PowTest extends Base {

  @Test()
  public void powTest() {
    double actual = calculator.pow(2.2, 2.3);
    assertEquals(actual, 4.840000000000001);
  }
}
