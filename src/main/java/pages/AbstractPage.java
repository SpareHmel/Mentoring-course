package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

public abstract class AbstractPage {

  protected WebDriver driver;
  protected WebDriverWait wait;
  protected static PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  protected static final String BASE_URL = propertyReader.readPropertyFile("baseUrl");
  protected Actions actions;
  JavascriptExecutor js;

  public abstract AbstractPage openPage();

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 10);
    actions = new Actions(driver);
    js = (JavascriptExecutor) this.driver;
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

  protected WebElement waitForClickable(WebElement webElement) {
    return wait.until(ExpectedConditions.elementToBeClickable(webElement));
  }

  protected void jsClick(WebElement webElement) {
    js.executeScript("arguments[0].click();", webElement);
  }

  protected void waitPageForLoad() {
    wait.until((ExpectedCondition<Boolean>) driver ->
        js.executeScript("return document.readyState").equals("complete"));
  }

  protected void scrollToElement(WebElement webElement) {
    js.executeScript("arguments[0].scrollIntoView();", webElement);
  }
}
