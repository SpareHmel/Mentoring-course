package stepdefs;

import driver_manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.HomePage;
import pages.SentPage;
import pages.TemplatePage;
import utils.PropertyReader;

public abstract class BaseStep {

  protected PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  protected HomePage homePage;
  protected BaseMailPage baseMailPage;
  protected DraftsPage draftsPage;
  protected SentPage sentPage;
  protected TemplatePage templatePage;
  protected static WebDriver driver = DriverManager.getDriver();

  protected BaseStep() {
    homePage = HomePage.getInstance(driver);
    baseMailPage = BaseMailPage.getInstance(driver);
    draftsPage = DraftsPage.getInstance(driver);
    sentPage = SentPage.getInstance(driver);
    templatePage = TemplatePage.getInstance(driver);
  }

  protected void isTitlePresentedWithText(String title) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains(title));
  }
}
