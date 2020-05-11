package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import user.User;

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
