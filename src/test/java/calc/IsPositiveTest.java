package calc;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class IsPositiveTest extends Base {

  @Test
  public void isPositiveTest() {
    boolean actual = calculator.isPositive(1);
    assertTrue(actual);
  }
}
