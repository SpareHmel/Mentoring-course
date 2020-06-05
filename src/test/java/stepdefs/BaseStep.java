package stepdefs;

import hooks.TestContext;
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

  protected BaseStep() {
    homePage = HomePage.getInstance(TestContext.getDriver());
    baseMailPage = BaseMailPage.getInstance(TestContext.getDriver());
    draftsPage = DraftsPage.getInstance(TestContext.getDriver());
    sentPage = SentPage.getInstance(TestContext.getDriver());
    templatePage = TemplatePage.getInstance(TestContext.getDriver());
  }

  protected void isTitlePresentedWithText(String title) {
    new WebDriverWait(TestContext.getDriver(), 10).until(ExpectedConditions.titleContains(title));
  }
}
