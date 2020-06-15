package pages;

import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import utils.PropertyReader;

public abstract class AbstractPage extends LoadableComponent<AbstractPage> {

  protected static PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");

  protected final WebDriver driver;
  protected static final String BASE_URL = propertyReader.readPropertyFile("baseUrl");
  protected final Actions actions;
  protected final Browser browser;

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    actions = new Actions(driver);
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
