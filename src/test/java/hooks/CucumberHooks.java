package hooks;

import cucumber.api.java.After;
import org.openqa.selenium.By;

public class CucumberHooks {

  @After
  public void tearDown() {
    TestContext.getDriver().findElement(By.id("PH_logoutLink")).click();
  }
}
