package pages;

import browser.Browser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.MyLogger;
import utils.PropertyReader;

public abstract class AbstractPage extends LoadableComponent<AbstractPage> {

  protected static PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");

  protected final WebDriver driver;
  protected final WebDriverWait wait;
  protected static final String BASE_URL = propertyReader.readPropertyFile("baseUrl");
  protected final Actions actions;
  protected final JavascriptExecutor js;
  protected final Browser browser;

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 10);
    actions = new Actions(driver);
    js = (JavascriptExecutor) this.driver;
    browser = Browser.getInstance();
  }

  @Override
  public AbstractPage get() {
    try {
      this.isLoaded();
      return this;
    } catch (Error var2) {
      this.load();
      acceptAlertIfPresent();
      this.isLoaded();
      return this;
    }
  }

  protected void alertHandling(WebElement webElement) {
    try {
      waitForPresence(webElement);
    } catch (UnhandledAlertException f) {
      try {
        Alert alert = driver.switchTo().alert();
        alert.accept();
      } catch (NoAlertPresentException ignore) {
      }
    }
  }

  protected void acceptAlertIfPresent() {
    try {
      wait.until(ExpectedConditions.alertIsPresent());
      Alert alert = driver.switchTo().alert();
      alert.accept();
    } catch (TimeoutException ignore) {
      MyLogger.error("No alerts were detected");
    }
  }

  protected WebElement waitForPresence(WebElement webElement) {
    return wait.until(ExpectedConditions.visibilityOf(webElement));
  }

  protected void waitPageForLoad() {
    wait.until((ExpectedCondition<Boolean>) driver ->
        js.executeScript("return document.readyState").equals("complete"));
  }

  protected void scrollToElement(WebElement webElement) {
    js.executeScript("arguments[0].scrollIntoView();", webElement);
    MyLogger.info("Scrolled to the element " + webElement.getText() + "located: " + webElement);
    browser.takeScreenshot();
  }

  protected void jsClick(WebElement webElement) {
    js.executeScript("arguments[0].click();", webElement);
    MyLogger.info("js click to the element" + webElement);
    browser.takeScreenshot();
  }
}
