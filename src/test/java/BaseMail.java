import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.HomePage;
import pages.SentPage;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseMail {

  PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
  protected WebDriver driver;
  protected String login;
  protected String password;
  protected BaseMailPage baseMailPage;
  protected HomePage homePage;
  protected DraftsPage draftsPage;
  protected SentPage sentPage;

  @BeforeSuite
  protected void setupDriverPath() {
    System.setProperty("webdriver.chrome.driver",
        Objects.requireNonNull(this.getClass().getClassLoader().getResource("chromedriver.exe")).getPath());
  }

  @BeforeMethod
  protected void setUp() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    driver = new ChromeDriver(chromeOptions);
    driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyReader
        .readPropertyFile("implicitlyWaitDefault")), TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(propertyReader.readPropertyFile("mailLink"));
    setSignInOptions();
  }

  @AfterMethod
  protected void tearDown() {
    driver.quit();
  }

  private void setSignInOptions() {
    login = propertyReader.readPropertyFile("login");
    password = propertyReader.readPropertyFile("password");
  }

  protected void isTitlePresentedWithText(String title) {
    new WebDriverWait(driver, 10).until(ExpectedConditions
        .titleContains(title));
  }
}
