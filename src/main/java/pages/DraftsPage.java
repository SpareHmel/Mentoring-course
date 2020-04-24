package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DraftsPage extends BaseMailPage {

  private static final String DRAFTS_PAGE_URL = "https://e.mail.ru/drafts/";

  public DraftsPage(WebDriver driver) {
    super(driver);
  }

  public DraftsPage openPage() {
    driver.get(DRAFTS_PAGE_URL);
    wait.until(ExpectedConditions.titleContains("Черновики - Почта Mail.ru"));
    return this;
  }

  @FindBy(xpath = "//div[@class='dataset__items']//span[@title='hmel25@bk.ru']")
  private WebElement draftsAddressee;

  @FindBy(css = "span[class='ll-sj__normal']")
  private WebElement draftsSubject;

  @FindBy(xpath = "//div[@class='dataset__items']//a[@data-id]")
  private WebElement draft;

  @FindBy(css = ".letter-list .octopus__title")
  private WebElement noDraftsMessage;

  public WebElement getDraftsAddressee() {
    return waitForPresence(draftsAddressee);
  }

  public WebElement getDraftsSubject() {
    return waitForPresence(draftsSubject);
  }

  public void selectDraftAndSendMail() {
    draft.click();
    fillBodyField(Keys.CONTROL, Keys.ENTER);
  }

  public String getNoDraftsMessage() {
    return waitForPresence(noDraftsMessage).getText();
  }
}
