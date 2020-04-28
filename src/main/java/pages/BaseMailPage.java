package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMailPage extends AbstractPage {

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

  @FindBy(xpath = "//div[@class='text--2BGkj']/..")
  private WebElement templateButton;

  @FindBy(css = ".control--2sQCu .ellipsis--2wDr9")
  private WebElement templateSaveButton;

  public BaseMailPage(WebDriver driver) {
    super(driver);
  }

  public BaseMailPage openPage() {
    driver.get(BASE_URL);
    return this;
  }

  public void startWritingLetter() {
    waitForPresence(writeLetterButton).click();
  }

  public void fillAddresseeField(String field) {
    waitForPresence(addresseeField).sendKeys(field);
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

  public void closeMessageWindow() {
    closeButton.click();
  }

  public void logOff() {
    logOffButton.click();
  }

  public void saveLetterAsTemplate() {
    templateButton.click();
    templateSaveButton.click();
  }

  public void sendMail() {
    fillBodyField(Keys.CONTROL, Keys.ENTER);
  }
}
