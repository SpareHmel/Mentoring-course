import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseMail {

  private final Properties property = new Properties();
  protected WebDriver driver;
  private String login;
  private String password;
  private String mailLink;
  private int implicitlyWaitDefault;

  @BeforeSuite
  protected void setupDriver() {
    System.setProperty("webdriver.chrome.driver",
        Objects.requireNonNull(this.getClass().getClassLoader().getResource("chromedriver.exe")).getPath());
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(implicitlyWaitDefault, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    fillUserDataFromPropertyFile();
    driver.get(mailLink);
    signIn();
  }

  @AfterSuite
  protected void tearDown() {
    driver.close();
  }

  protected void signIn() {
    driver.findElement(By.id("mailbox:login")).sendKeys(login);
    driver.findElement(By.cssSelector(".o-control[type='submit']")).click();
    waitForClickable(By.id("mailbox:password")).sendKeys(password);
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

  protected void fillUserDataFromPropertyFile() {
    try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
      property.load(fis);
      login = property.getProperty("login");
      password = property.getProperty("password");
      mailLink = property.getProperty("mailLink");
      implicitlyWaitDefault = Integer.parseInt(property.getProperty("implicitlyWaitDefault"));
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Property file is not found");
    }
  }
}
