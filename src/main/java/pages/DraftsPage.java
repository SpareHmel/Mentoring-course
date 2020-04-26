package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftsPage extends BaseMailPage {

  private static final String DRAFTS_PAGE_URL = BASE_URL + "/drafts";

//  @FindBy(xpath = "//div[@class='dataset__items']//span[@title='hmel25@bk.ru']")
//  @FindBy(xpath = "//a[contains(@href, '/drafts/0')]//span[@class='Hi5s283']")
  @FindBy(xpath = "//a[contains(@href, '/drafts/0')]")
  private WebElement draftsAddressee;

  @FindBy(css = "span[class='ll-sj__normal']")
  private WebElement draftsSubject;

  @FindBy(xpath = "//div[@class='dataset__items']//a[@data-id]")
  private WebElement draft;

  @FindBy(css = ".letter-list .octopus__title")
  private WebElement noDraftsMessage;

  public DraftsPage(WebDriver driver) {
    super(driver);
  }

  public DraftsPage openPage() {
    driver.get(DRAFTS_PAGE_URL);
    return this;
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
    fillBodyField(Keys.CONTROL, Keys.ENTER);
  }

  public String getNoDraftsMessageText() {
    return waitForPresence(noDraftsMessage).getText();
  }
}
