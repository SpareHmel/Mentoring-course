package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CosTest extends Base {

  @Test()
  public void cosTest() {
    double actual = calculator.cos(0);
    assertEquals(actual, 0.0);
  }
}
