package pages;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatePage extends BaseMailPage {

  private static final String TEMPLATE_PAGE_URL = BASE_URL + "/templates";

  @FindBy(css = ".dataset-letters a")
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
    template.click();
  }
}
