package pages;

import static org.testng.AssertJUnit.assertTrue;

import entities.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

  private static final String HOMEPAGE_URL = propertyReader.readPropertyFile("mailLink");
  private static HomePage instance;

  @FindBy(id = "mailbox:login")
  private WebElement loginField;

  @FindBy(id = "mailbox:password")
  private WebElement passwordField;

  @FindBy(css = ".o-control[type='submit']")
  private WebElement submitButton;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public static HomePage getInstance(WebDriver driver) {
    if (instance == null || instance.driver != driver) {
      instance = new HomePage(driver);
    }
    return instance;
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
    waitPageForLoad();
    loginField.clear();
    loginField.sendKeys(user.getLogin());
    waitPageForLoad();
    submitButton.click();
    passwordField.sendKeys(user.getPassword());
    submitButton.click();
  }
}
