package pages;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import reporting.MyLogger;

public class DraftsPage extends BaseMailPage {

  private static final String DRAFTS_PAGE_URL = BASE_URL + "/drafts";

  @FindBy(xpath = "//a[contains(@href, '/drafts/0')]")
  private WebElement draftsAddressee;

  @FindBy(css = "span[class='ll-sj__normal']")
  private WebElement draftsSubject;

  @FindBy(xpath = "//div[@class='dataset__items']//a[@data-id]")
  private WebElement draft;

  @FindBy(css = ".letter-list .octopus__title")
  private WebElement noDraftsMessage;

  @FindBy(css = ".dataset__items .mb-checkbox__block")
  private WebElement draftCheckbox;

  @FindBy(css = "span[data-title-shortcut='Del']")
  private WebElement deleteDraftButton;

  public DraftsPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected void load() {
    driver.get(DRAFTS_PAGE_URL);
  }

  @Override
  protected void isLoaded() throws AssertionError {
    assertTrue("DraftsPage isn't loaded", driver.getCurrentUrl().contains(DRAFTS_PAGE_URL));
  }

  public boolean isDraftsAddresseeDisplayed() {
    browser.alertHandling(draftsAddressee);
    return draftsAddressee.isDisplayed();
  }

  public String getDraftsSubjectText() {
    return browser.waitForPresence(draftsSubject).getText();
  }

  public void selectDraftAndSendMail() {
    browser.click(draft);
    sendMail();
    MyLogger.info("Draft was selected then sent");
  }

  public String getNoDraftsMessageText() {
    return browser.waitForPresence(noDraftsMessage).getText();
  }

  public void deleteDraft() {
    browser.jsClick(draftCheckbox);
    deleteDraftButton.click();
    MyLogger.info("Selected draft was deleted");
  }
}
