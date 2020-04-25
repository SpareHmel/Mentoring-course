package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

  protected WebDriver driver;
  protected WebDriverWait wait;

  public abstract AbstractPage openPage();

  public AbstractPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 10);
  }

  protected void alertHandling(WebElement webElement) {
    try {
      waitForPresence(webElement);
    } catch (UnhandledAlertException f) {
      try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert data: " + alertText);
        alert.accept();
      } catch (NoAlertPresentException e) {
        e.printStackTrace();
      }
    }
  }

  protected WebElement waitForPresence(WebElement webElement) {
    return wait.until(ExpectedConditions.visibilityOf(webElement));
  }
}
