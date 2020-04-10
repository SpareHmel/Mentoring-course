package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class SqrtTest extends Base {

  @Test()
  public void sqrtTest() {
    double actual = calculator.sqrt(4);
    assertEquals(actual, 2);
  }
}
