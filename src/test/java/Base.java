import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Base {

  protected WebDriver driver;

  @BeforeSuite
  protected void setupDriver() {
    driver = new ChromeDriver();
    String gmailSignInLink = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%"
        + "2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    driver.get(gmailSignInLink);
    signIn();
  }

  @AfterSuite
  protected void tearDown() {
    driver.close();
  }

  protected void signIn() {
    driver.findElement(By.id("identifierId")).sendKeys("testrtgbmailfbi@gmail.com");
    driver.findElement(By.xpath("//span[text()='Далее']")).click();
    waitForClickable(By.xpath("//input[@name='password']")).sendKeys("reliable1234");
    driver.findElement(By.id("passwordNext")).click();
  }

  protected WebElement waitForPresence(By by) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
  }

  protected WebElement waitForClickable(By by) {
    return new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(by));
  }
}
