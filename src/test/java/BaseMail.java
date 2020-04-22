import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseMail {

  protected WebDriver driver;

  @BeforeSuite
  protected void setupDriverPath() {
    System.setProperty("webdriver.chrome.driver",
        Objects.requireNonNull(this.getClass().getClassLoader().getResource("chromedriver.exe")).getPath());
  }

  @BeforeMethod
  protected void setUp() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyReader
        .readPropertyFile("implicitlyWaitDefault")), TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(PropertyReader.readPropertyFile("mailLink"));
    signIn();
  }

  @AfterMethod
  protected void tearDown() {
    driver.quit();
  }

  protected void signIn() {
    driver.findElement(By.id("mailbox:login")).sendKeys(PropertyReader.readPropertyFile("login"));
    driver.findElement(By.cssSelector(".o-control[type='submit']")).click();
    waitForClickable(By.id("mailbox:password")).sendKeys(PropertyReader.readPropertyFile("password"));
    driver.findElement(By.cssSelector(".o-control[type='submit']")).click();
  }

  protected WebElement waitForPresence(By by) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
  }

  protected WebElement waitForClickable(By by) {
    return new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(by));
  }

  protected void isTitlePresentedWithText(String title) {
    new WebDriverWait(driver, 10).until(ExpectedConditions
        .titleContains(title));
  }
}
