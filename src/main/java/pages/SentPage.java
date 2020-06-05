package pages;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentPage extends BaseMailPage {

  private static final String INBOX_PAGE_URL = BASE_URL + "/sent";
  private static SentPage instance;

  @FindBy(xpath = "//div[@class='dataset__items']//a[@data-id]")
  private WebElement mail;

  @FindBy(xpath = "(//div[@class='dataset__items']//a[@data-id])[last()]")
  private WebElement lastSentMail;

  public SentPage(WebDriver driver) {
    super(driver);
  }

  public static SentPage getInstance(WebDriver driver) {
    if (instance == null || instance.driver != driver) {
      instance = new SentPage(driver);
    }
    return instance;
  }

  @Override
  protected void load() {
    driver.get(INBOX_PAGE_URL);
  }

  @Override
  protected void isLoaded() throws AssertionError {
    assertTrue("InboxPage isn't loaded", driver.getCurrentUrl().contains(INBOX_PAGE_URL));
  }

  public String getMailDetailsText() {
    alertHandling(mail);
    return mail.getText();
  }

  public void scrollToLastSentMail() {
    scrollToElement(lastSentMail);
  }

  public boolean isSentMailDisplayed() {
    return waitForPresence(lastSentMail).isDisplayed();
  }
}
