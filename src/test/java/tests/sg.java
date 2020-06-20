package tests;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import factory.Mail;
import factory.MailFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.SentPage;
import pages.TemplatePage;

import static driver_manager.DriverManager.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({ReportPortalTestNGListener.class})
public class sg extends BaseMailTest {

  private final String subject = "Elimination details";
  private final String title = "Входящие - Почта Mail.ru";
  private final String noDrafts = "У вас нет незаконченных\nили неотправленных писем";


  @Test(description = "This test should fail")
  public void saveAsTemplateAndSendMail() {
    isTitlePresentedWithText(title);
    baseMailPage = new BaseMailPage(getDriver());
    Mail mail = MailFactory.mailWithCustomBody("important text");
    baseMailPage.startWritingLetter();
    baseMailPage.fillInMailFields(mail);
    baseMailPage.saveLetterAsTemplate();
    templatePage = new TemplatePage(getDriver());
    templatePage.get();
    templatePage.openTemplate();
    templatePage.sendMail();
    sentPage = new SentPage(getDriver());
    sentPage.get();
    assertTrue(sentPage.getMailDetailsText().contains(subject));
  }

  @Test
  public void scrollToLastSentMail() {
    isTitlePresentedWithText(title);
    sentPage = new SentPage(getDriver());
    sentPage.get();
    sentPage.scrollToLastSentMail();
    assertTrue(sentPage.isSentMailDisplayed());
  }
}
