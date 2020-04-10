package calc;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CtgTest extends Base {

  @Test()
  public void ctgTest() {
    double actual = calculator.ctg(8);
    assertEquals(actual, 0.9999997749296758);
  }
}
