package hooks;

import org.openqa.selenium.WebDriver;

public class TestContext {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    return driver;
  }

  public static void setDriver(WebDriver driver) {
    TestContext.driver = driver;
  }
}
