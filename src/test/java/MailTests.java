import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class MailTests extends BaseMail {

  @Test
  public void POtest() throws InterruptedException {
    final String addressee = "hmel25@bk.ru";
    final String subject = "Elimination details";
    final String body = "4815162342";
    isTitlePresentedWithText("Входящие - Почта Mail.ru");
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    baseMailPage.saveLetterAsDraft();
    baseMailPage.closeMessageWindow(); // тут задержка
    Thread.sleep(2000);
    //нужет waiter
    //https://stackoverflow.com/questions/47191185/how-to-explicitly-wait-while-using-page-factory-in-selenium
    draftsPage.openPage();
    assertTrue(draftsPage.getDraftsAddressee().isDisplayed());
    assertEquals(draftsPage.getDraftsSubject().getText(), subject);
    draftsPage.selectDraftAndSendMail();
    Thread.sleep(2000);
    sentPage.openPage();
    assertTrue(sentPage.getMail().getText().contains(subject));
    draftsPage.openPage();
    assertEquals(draftsPage.getNoDraftsMessage(), "У вас нет незаконченных\nили неотправленных писем");
    baseMailPage.logOff();

  }

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
    driver.findElement(By.cssSelector("div[class*=subject__container] input")).sendKeys(subject);
    driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(body);
    //Save the mail as a draft.
    driver.findElement(By.xpath("//*[@data-title-shortcut='Ctrl+S']/span")).click();
    driver.findElement(By.cssSelector(".compose-windows button[tabindex='700']")).click();
    waitForPresence(By.cssSelector("a[href='/drafts/'] .nav__folder-name__txt")).click();
    //Verify the draft content (addressee, subject and body – should be the same as in 3).
    assertTrue(waitForPresence(By.xpath("//div[@class='dataset__items']//span[@title='hmel25@bk.ru']")).isDisplayed());
    assertEquals(waitForPresence(By.cssSelector("span[class='ll-sj__normal']")).getText(), subject);
    //Send the mail.
    driver.findElement(By.xpath("//div[@class='dataset__items']//a[@data-id]")).click();
    waitForPresence(By.xpath("//div[@role='textbox']")).sendKeys(Keys.CONTROL, Keys.ENTER);
    //Verify, that the mail is in ‘Sent’ folder
    waitForPresence(By.cssSelector(".layer__link")).click();
    assertEquals(waitForPresence(By.cssSelector(".thread__subject")).getText(), subject);
    //Verify, that the mail disappeared from ‘Drafts’ folder.
    waitForPresence(By.cssSelector("a[href='/drafts/'] .nav__folder-name__txt")).click();
    assertEquals(waitForPresence(By.cssSelector(".letter-list .octopus__title")).getText(),
        "У вас нет незаконченных\nили неотправленных писем");
    //Log off.
    driver.findElement(By.id("PH_logoutLink")).click();
  }
}
