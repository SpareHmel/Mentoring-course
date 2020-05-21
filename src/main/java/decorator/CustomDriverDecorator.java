package decorator;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class CustomDriverDecorator implements WebDriver, JavascriptExecutor {

  protected WebDriver driver;
  protected JavascriptExecutor js;

  public CustomDriverDecorator(WebDriver driver) {
    this.driver = driver;
    js = (JavascriptExecutor) driver;
  }

  public void get(String url) {
    driver.get(url);
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  public WebElement findElement(By by) {
    Reporter.log(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()),
        true);
    return driver.findElement(by);
  }

  public String getPageSource() {
    return driver.getPageSource();
  }

  public void close() {
    driver.close();
  }

  public void quit() {
    System.out.println("Browser will be closed now...");
    driver.quit();
  }

  public Set<String> getWindowHandles() {
    return driver.getWindowHandles();
  }

  public String getWindowHandle() {
    return driver.getWindowHandle();
  }

  public TargetLocator switchTo() {
    return driver.switchTo();
  }

  public Navigation navigate() {
    return driver.navigate();
  }

  public Options manage() {
    return driver.manage();
  }

  public Object executeScript(String var1, Object... var2) {
    return js.executeScript(var1, var2);
  }

  public Object executeAsyncScript(String var1, Object... var2) {
    return js.executeAsyncScript(var1, var2);
  }
}
