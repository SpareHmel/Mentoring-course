package pages;

import static org.testng.AssertJUnit.assertTrue;

import factory.Mail;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMailPage extends AbstractPage {

  private static BaseMailPage instance;

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

  @FindBy(xpath = "//div[contains(@class, 'datalist_visible')]//div[contains(@class, 'control')]")
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

  public static BaseMailPage getInstance(WebDriver driver) {
    if (instance == null || instance.driver != driver) {
      instance = new BaseMailPage(driver);
    }
    return instance;
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
  }

  public void saveMailAsTemplate() {
    templateButton.click();
    templateSaveButton.click();
  }

  public void sendMail() {
    bodyField.sendKeys(Keys.CONTROL, Keys.RETURN);
  }

  public void moveAddresseeToCopy() {
    copyAddresseeButton.click();
    actions.dragAndDrop(addressee, copyField).build().perform();
  }

  public boolean checkAddresseeVisibility() {
    return waitForPresence(copyFieldAddressee).isDisplayed();
  }

  public void fillInDesiredFields(Mail mail) {
    waitForPresence(addresseeField).sendKeys(mail.getAddressee());
    if (mail.getSubject() != null) {
      subjectField.sendKeys(mail.getSubject());
    }
    bodyField.sendKeys(mail.getBody());
  }
}
