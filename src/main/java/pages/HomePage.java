package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

  private static final String HOMEPAGE_URL = "https://e.mail.ru/inbox/";

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "mailbox:login")
  private WebElement loginField;

  @FindBy(css = "mailbox:password")
  private WebElement passwordField;

  @FindBy(css = ".o-control[type='submit']")
  private WebElement submitButton;

  public HomePage openPage() {
    driver.get(HOMEPAGE_URL);
    return this;
  }

  public void enterLogin(String login) {
    loginField.sendKeys(login);
    submitButton.click();
  }

  public void enterPassword(String password) {
    passwordField.sendKeys(password);
    submitButton.click();
  }
}
