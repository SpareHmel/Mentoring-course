package calc;

import static org.testng.Assert.assertFalse;

import org.testng.annotations.Test;

public class IsNegative extends Base {

  @Test
  public void isNegativeTest() {
    boolean actual = calculator.isNegative(1);
    assertFalse(actual);
  }

}
