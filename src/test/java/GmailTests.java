import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GmailTests extends Base {

  @Test
  public void gmailTest() {
    final String addressee = "hmel25@bk.ru";
    final String subject = "Elimination details";
    final String body = "4815162342";
    //Assert, that the login is successful.
    assertTrue(new WebDriverWait(driver, 10).until(ExpectedConditions
        .titleIs("Входящие - testrtgbmailfbi@gmail.com - Gmail")));
    //Create a new mail (fill addressee, subject and body fields).
    waitForClickable(By.xpath("//div[text()='Написать']")).click();
    waitForPresence(By.xpath("//textarea[@name='to']")).sendKeys(addressee);
    driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subject);
    driver.findElement(By.xpath("//div[@aria-label='Тело письма']")).sendKeys(body);
    //Save the mail as a draft.
    driver.findElement(By.xpath("//img[@aria-label='Сохранить и закрыть']")).click();
    //Verify, that the mail presents in ‘Drafts’ folder.
    driver.findElement(By.xpath("//a[@title='Черновики']")).click();
    //Verify the draft content (addressee, subject and body – should be the same as in 3).
    assertTrue(waitForPresence(By.xpath("//span[text()='Elimination details']")).isEnabled());
    assertTrue(waitForPresence(By.xpath("//span[text()='4815162342']")).isEnabled());
    waitForClickable(By.xpath("//span[contains(text(), 'Elimination details')]/../..")).click();
    //Send the mail.
    waitForClickable(By.xpath("//div[@aria-label='Тело письма']")).sendKeys(Keys.CONTROL, Keys.ENTER);
    //Verify, that the mail disappeared from ‘Drafts’ folder.
    assertTrue(waitForPresence(By.xpath("//td[text()='Нет сохраненных черновиков.']")).isDisplayed());
    //Verify, that the mail is in ‘Sent’ folder.
    driver.findElement(By.xpath("//a[text()='Отправленные']")).click();
    assertTrue(waitForPresence(By.xpath("//span[contains(text(), 'Elimination details')]")).isEnabled());
    //Log off.
    driver.findElement(By.xpath("//a[contains(@aria-label, 'Аккаунт Google')]")).click();
    waitForPresence(By.xpath("//a[text()='Выйти']")).click();
  }
}
