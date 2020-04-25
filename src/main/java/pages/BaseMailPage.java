package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMailPage extends AbstractPage {

  private static final String INBOX_PAGE_URL = "https://e.mail.ru/";

  public BaseMailPage(WebDriver driver) {
    super(driver);
  }

  public BaseMailPage openPage() {
    driver.get(INBOX_PAGE_URL);
    return this;
  }

  @FindBy(className = "compose-button__txt")
  private WebElement writeLetterButton;

  @FindBy(css = "div[data-name='to'] input")
  private WebElement addresseeField;

  @FindBy(css = "div[class*=subject__container] input")
  private WebElement subjectField;

  @FindBy(xpath = "//div[@role='textbox']")
  private WebElement bodyField;

  @FindBy(xpath = "//*[@data-title-shortcut='Ctrl+S']/span")
  private WebElement saveAsDraftButton;

  @FindBy(css = ".compose-windows button[tabindex='700']")
  private WebElement closeButton;

  @FindBy(id = "PH_logoutLink")
  private WebElement logOffButton;

  public void startWritingLetter() {
    writeLetterButton.click();
  }

  public void fillAddresseeField(String field) {
    addresseeField.sendKeys(field);
  }

  public void fillSubjectField(String subject) {
    subjectField.sendKeys(subject);
  }

  public void fillBodyField(CharSequence... body) {
    bodyField.sendKeys(body);
  }

  public void saveMailAsDraft() {
    saveAsDraftButton.click();
  }

  public void sendLetter() {
    bodyField.sendKeys(Keys.CONTROL, Keys.ENTER);
  }

  public void closeMessageWindow() {
    closeButton.click();
  }

  public void logOff() {
    logOffButton.click();
  }
}
