package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentPage extends BaseMailPage {

  private static final String INBOX_PAGE_URL = BASE_URL + "/sent";

  @FindBy(xpath = "//div[@class='dataset__items']//a[@data-id]")
  private WebElement mail;

  public SentPage(WebDriver driver) {
    super(driver);
  }

  public SentPage openPage() {
    driver.get(INBOX_PAGE_URL);
    return this;
  }

  public String getMailDetailsText() {
    alertHandling(mail);
    return mail.getText();
  }
}
