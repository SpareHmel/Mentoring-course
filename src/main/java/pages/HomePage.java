package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  public HomePage openPage() {
    driver.get(HOMEPAGE_URL);
    return this;
  }

  public void signIn(String login, String password) {
    loginField.clear();
    loginField.sendKeys(login);
    submitButton.click();
    waitForPresence(passwordField).sendKeys(password);
    submitButton.click();
  }
}
