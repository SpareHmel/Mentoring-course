import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import utils.PropertyReader;

public class BaseMailTest {

  PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  private static WebDriver driver;
  protected String login;
  protected String password;
  protected BaseMailPage baseMailPage;
  protected HomePage homePage;
  protected DraftsPage draftsPage;
  protected SentPage sentPage;
  protected TemplatePage templatePage;

  public static WebDriver getDriver() {
    if (driver == null) {
      setDriver();
    }
    return driver;
  }

  private static void setDriver() {
    WebDriverManager.chromedriver().setup();
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    capabilities.setCapability("platform", Platform.WINDOWS);
    capabilities.setCapability("browserName", BrowserType.CHROME);
    capabilities.setCapability("javascriptEnabled", true);
    try {
      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @BeforeClass
  protected void setUp() {
    driver = getDriver();
    driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader
        .readPropertyFile("implicitlyWaitDefault")), TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @BeforeMethod
  protected void signIn() {
    driver.get(propertyReader.readPropertyFile("mailLink"));
    setSignInOptions();
    homePage = new HomePage(driver);
    homePage.signIn(login, password);
  }

  @AfterMethod
  protected void logOff() {
    getDriver().findElement(By.id("PH_logoutLink")).click();
  }

  @AfterClass
  protected void tearDown() {
    driver.quit();
  }

  private void setSignInOptions() {
    login = propertyReader.readPropertyFile("login");
    password = propertyReader.readPropertyFile("password");
  }

  protected void isTitlePresentedWithText(String title) {
    new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains(title));
  }
}
