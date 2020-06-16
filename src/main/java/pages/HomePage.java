package pages;

import static org.testng.AssertJUnit.assertTrue;

import entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import reporting.MyLogger;

public class HomePage extends AbstractPage {

  private static final String HOMEPAGE_URL = propertyReader.readPropertyFile("mailLink");

  @FindBy(id = "mailbox:login")
  private WebElement loginField;

  @FindBy(id = "mailbox:password")
  private WebElement passwordField;

  @FindBy(css = ".o-control[type='submit']")
  private WebElement submitButton;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected void load() {
    driver.get(HOMEPAGE_URL);
  }

  @Override
  protected void isLoaded() throws AssertionError {
    assertTrue("HomePage isn't loaded", driver.getCurrentUrl().contains(HOMEPAGE_URL));
  }

  public void signIn(User user) {
    MyLogger.info("Logging in");
    browser.waitPageForLoad();
    loginField.clear();
    browser.sendKeys(loginField, user.getLogin());
    browser.waitPageForLoad();
    browser.click(submitButton);
    browser.sendKeys(passwordField, user.getPassword());
    browser.click(submitButton);
  }
}
