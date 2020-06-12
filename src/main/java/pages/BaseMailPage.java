package pages;

import static org.testng.AssertJUnit.assertTrue;

import factory.Mail;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import reporting.MyLogger;

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

  @FindBy(xpath = "(//*[contains(@class, 'editor_container')]//div[contains(@class, 'withBorder')]//button)[last()]")
  private WebElement templateButton;

//  @FindBy(xpath = "//div[contains(@class, 'datalist_visible')]//div[contains(@class, 'control')]")
@FindBy(xpath = "//div[@data-test-id='templates']//div[@class='control--2sQCu']")
private WebElement templateSaveButton;

  @FindBy(xpath = "//div[@class='contactsControls--3iwxE']//button[1]")
  private WebElement copyAddresseeButton;

  @FindBy(xpath = "//span[@class='text--1tHKB']/..")
  private WebElement addressee;

  @FindBy(xpath = "(//div[@class='contactsContainer--3RMuQ'])[2]")
  private WebElement copyField;

  @FindBy(xpath = "//div[@class='container--3B3Lm status_base--wsRcM']/..")
  private WebElement copyFieldAddressee;

  public BaseMailPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected void load() {
    driver.get(BASE_URL);
  }

  @Override
  protected void isLoaded() throws AssertionError {
    assertTrue("BaseMailPage isn't loaded", driver.getCurrentUrl().contains(BASE_URL));
  }

  public void startWritingLetter() {
    waitForPresence(writeLetterButton).click();
  }

  public void saveMailAsDraft() {
    saveAsDraftButton.click();
  }

  public void closeMessageWindow() {
    closeButton.click();
    acceptAlertIfPresent();
    MyLogger.info("Message window is closed");
  }

  public void saveLetterAsTemplate() {
//    templateButton.click();
//    templateSaveButton.click();
    browser.click(templateButton);
    browser.click(templateSaveButton);
    MyLogger.info("Letter was saved as template");
  }

  public void sendMail() {
    bodyField.sendKeys(Keys.CONTROL, Keys.RETURN);
    MyLogger.info("Mail was sent");
  }

  public void moveAddresseeToCopy() {
    copyAddresseeButton.click();
    actions.dragAndDrop(addressee, copyField).build().perform();
    MyLogger.info("Addressee moved to the copy field");
  }

  public boolean checkAddresseeVisibility() {
    return waitForPresence(copyFieldAddressee).isDisplayed();
  }

  public void fillInMailFields(Mail mail) {
    waitForPresence(addresseeField).sendKeys(mail.getAddressee());
    MyLogger.info("Addressee was filled with text: " + mail.getAddressee());
    if (mail.getSubject() != null) {
      subjectField.sendKeys(mail.getSubject());
      MyLogger.info("Subject was filled with text: " + mail.getSubject());
    }
    bodyField.sendKeys(mail.getBody());
    MyLogger.info("Body was filled with text: " + mail.getBody());
  }
}
