package calculator.arithmetic;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcJUnitBase;
import org.junit.Test;

public class DivTest extends CalcJUnitBase {

  //junit demo

  @Test
  public void divisionTest() {
    long actual = calculator.div(22, 2);
    long expected = 22 / 2;
    assertEquals(actual, expected, "invalid result of div operation");
  }
}
