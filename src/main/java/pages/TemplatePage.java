package pages;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import reporting.MyLogger;

public class TemplatePage extends BaseMailPage {

  private static final String TEMPLATE_PAGE_URL = BASE_URL + "/templates";

  @FindBy(xpath = "//div[@class='I4X89ib']//a[@tabindex='-1']/div[@class='I4X89tp']")
  private WebElement template;

  public TemplatePage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected void load() {
    driver.get(TEMPLATE_PAGE_URL);
  }

  @Override
  protected void isLoaded() throws AssertionError {
    assertTrue("TemplatePage isn't loaded", driver.getCurrentUrl().contains(TEMPLATE_PAGE_URL));
  }

  public void openTemplate() {
    MyLogger.info("Opening template");
    browser.click(template);
  }
}
