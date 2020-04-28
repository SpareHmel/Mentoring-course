package pages;

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

  public TemplatePage openPage() {
    driver.get(TEMPLATE_PAGE_URL);
    return this;
  }

  public void openTemplate() {
    alertHandling(template);
    template.click();
  }
}
