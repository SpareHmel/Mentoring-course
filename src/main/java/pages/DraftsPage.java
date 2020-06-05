package pages;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftsPage extends BaseMailPage {

  private static final String DRAFTS_PAGE_URL = BASE_URL + "/drafts";
  private static DraftsPage instance;

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

  public static DraftsPage getInstance(WebDriver driver) {
    if (instance == null || instance.driver != driver) {
      instance = new DraftsPage(driver);
    }
    return instance;
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
    alertHandling(draftsAddressee);
    return draftsAddressee.isDisplayed();
  }

  public String getDraftsSubjectText() {
    return waitForPresence(draftsSubject).getText();
  }

  public void selectDraftAndSendMail() {
    draft.click();
    sendMail();
  }

  public String getNoDraftsMessageText() {
    return waitForPresence(noDraftsMessage).getText();
  }

  public void deleteDraft() {
    jsClick(draftCheckbox);
    deleteDraftButton.click();
  }
}
