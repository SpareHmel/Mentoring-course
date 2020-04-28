package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

public abstract class AbstractPage {

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected static PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  protected static final String BASE_URL = propertyReader.readPropertyFile("baseUrl");
  protected Actions action;

  public abstract AbstractPage openPage();


  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 10);
    action = new Actions(driver);
  }

  protected void alertHandling(WebElement webElement) {
    try {
      waitForPresence(webElement);
    } catch (UnhandledAlertException f) {
      try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
        alert.accept();
      } catch (NoAlertPresentException e) {
        e.printStackTrace();
      }
    }
  }

  protected WebElement waitForPresence(WebElement webElement) {
    return wait.until(ExpectedConditions.visibilityOf(webElement));
  }
}
