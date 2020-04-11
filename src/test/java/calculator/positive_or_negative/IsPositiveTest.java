package calculator.positive_or_negative;

import static org.testng.Assert.assertEquals;

import calculator.base.CalcBase;
import java.util.stream.Stream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IsPositiveTest extends CalcBase {

  @DataProvider
  public static Object [] calcData() {
    return new Object[] {12, 3, 0};
  }

  @Test(dataProvider = "calcData")
  public void isPositiveTest(long number) {
    boolean actual = calculator.isPositive(number);
    boolean expected = Stream.of(number).anyMatch(n -> n > 0L);
    assertEquals(actual, expected, "invalid result of isPositive operation");
  }
}
