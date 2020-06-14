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
//  protected final WebDriverWait wait;
  protected static final String BASE_URL = propertyReader.readPropertyFile("baseUrl");
  protected final Actions actions;
//  protected final JavascriptExecutor js;
  protected final Browser browser;

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
//    wait = new WebDriverWait(driver, 10);
    actions = new Actions(driver);
//    js = (JavascriptExecutor) this.driver;
    browser = Browser.getInstance();
  }

  @Override
  public AbstractPage get() {
    try {
      this.isLoaded();
      return this;
    } catch (Error var2) {
      this.load();
      browser.acceptAlertIfPresent();
      this.isLoaded();
      return this;
    }
  }
}
