package tests;

import driverManager.DriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.HomePage;
import pages.SentPage;
import pages.TemplatePage;
import user.User;
import utils.PropertyReader;

public class BaseMailTest {

  public static PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  private static WebDriver driver;
  protected String login;
  protected String password;
  protected BaseMailPage baseMailPage;
  protected HomePage homePage;
  protected DraftsPage draftsPage;
  protected SentPage sentPage;
  protected TemplatePage templatePage;

  @BeforeClass
  protected void setUp() {
    driver = DriverManager.getDriver();
    driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader
        .readPropertyFile("implicitlyWaitDefault")), TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @BeforeMethod
  protected void signIn() {
    driver.get(propertyReader.readPropertyFile("mailLink"));
    setSignInOptions(new User());
    homePage = new HomePage(driver);
    homePage.signIn(login, password);
  }

  @AfterMethod
  protected void logOff() {
    DriverManager.getDriver().findElement(By.id("PH_logoutLink")).click();
  }

  @AfterClass
  protected void tearDown() {
    driver.quit();
  }

  private void setSignInOptions(User user) {
    login = user.getLogin();
    password = user.getPassword();
  }

  protected void isTitlePresentedWithText(String title) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains(title));
  }
}
