import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MailTests extends BaseMail {

  @Test
  public void mailTest() {
    final String addressee = "hmel25@bk.ru";
    final String subject = "Elimination details";
    final String body = "4815162342";
    //Assert, that the login is successful.
    isTitlePresentedWithText("Входящие - Почта Mail.ru");
    //Create a new mail (fill addressee, subject and body fields).
    waitForClickable(By.className("compose-button__txt")).click();
    waitForPresence(By.cssSelector("div[data-name='to'] input")).sendKeys(addressee);
    driver.findElement(By.cssSelector(".container--3QXHv input")).sendKeys(subject);
    driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(body);
    //Save the mail as a draft.
    driver.findElement(By.xpath("//*[@data-title-shortcut='Ctrl+S']/span")).click();
    driver.findElement(By.cssSelector("button[title='Закрыть']")).click();
    waitForPresence(By.cssSelector("a[href='/drafts/'] .nav__folder-name__txt")).click();
    //Verify the draft content (addressee, subject and body – should be the same as in 3).
    assertTrue(waitForPresence(By.xpath("//div[@class='dataset__items']//span[@title='hmel25@bk.ru']")).isDisplayed());
    assertEquals(waitForPresence(By.cssSelector("span[class='ll-sj__normal']")).getText(), subject);
    //Send the mail.
    driver.findElement(By.xpath("//div[@class='dataset__items']//a[@data-id]")).click();
    waitForPresence(By.xpath("//div[@role='textbox']")).sendKeys(Keys.CONTROL, Keys.ENTER);
    //Verify, that the mail is in ‘Sent’ folder
    waitForPresence(By.cssSelector(".layer__link")).click();
    assertEquals(waitForPresence(By.cssSelector(".dataset-letters .ll-sj__normal")).getText(), subject);
    //Verify, that the mail disappeared from ‘Drafts’ folder.
    waitForPresence(By.cssSelector("a[href='/drafts/'] .nav__folder-name__txt")).click();
    assertEquals(waitForPresence(By.cssSelector(".letter-list .octopus__title")).getText(),
        "У вас нет незаконченных" + "\n" + "или неотправленных писем");
    //Log off.
    driver.findElement(By.id("PH_logoutLink")).click();
  }
}
