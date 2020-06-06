package hooks;

import cucumber.api.java.After;
import driver_manager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CucumberHooks {

  private static WebDriver driver = DriverManager.getDriver();

  @After
  public void tearDown() {
    driver.findElement(By.id("PH_logoutLink")).click();
  }
}
