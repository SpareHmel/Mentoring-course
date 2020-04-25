package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends BaseMailPage {

  private static final String INBOX_PAGE_URL = "https://e.mail.ru/inbox/";

  public InboxPage(WebDriver driver) {
    super(driver);
  }

  public InboxPage openPage() {
    driver.get(INBOX_PAGE_URL);
    return this;
  }

  @FindBy(xpath = "//div[@class='dataset__items']//a[@data-id]")
  private WebElement mail;
}
