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
    browser.waitForPresence(writeLetterButton).click();
  }

  public void saveMailAsDraft() {
    saveAsDraftButton.click();
  }

  public void closeMessageWindow() {
    MyLogger.info("Closing message window");
    closeButton.click();
    browser.acceptAlertIfPresent();
  }

  public void saveLetterAsTemplate() {
    MyLogger.info("Saving letter as template");
    browser.click(templateButton);
    browser.click(templateSaveButton);
  }

  public void sendMail() {
    MyLogger.info("Sending mail");
    bodyField.sendKeys(Keys.CONTROL, Keys.RETURN);
  }

  public void moveAddresseeToCopy() {
    MyLogger.info("Addressee is moving to the copy field");
    copyAddresseeButton.click();
    actions.dragAndDrop(addressee, copyField).build().perform();
  }

  public boolean checkAddresseeVisibility() {
    return browser.waitForPresence(copyFieldAddressee).isDisplayed();
  }

  public void fillInMailFields(Mail mail) {
    MyLogger.info("Filling addressee with text: " + mail.getAddressee());
    browser.waitForPresence(addresseeField);
    browser.sendKeys(addresseeField, mail.getAddressee());
    if (mail.getSubject() != null) {
      MyLogger.info("Filling subject with text: " + mail.getSubject());
      browser.sendKeys(subjectField, mail.getSubject());
    }
    MyLogger.info("Filling body with text: " + mail.getBody());
    browser.sendKeys(bodyField, mail.getBody());
  }
}
