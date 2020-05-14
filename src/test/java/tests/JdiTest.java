package tests;

import static org.hamcrest.Matchers.containsString;
import static sections.MailRuStaticSite.baseMailPage;
import static sections.MailRuStaticSite.draftsPage;
import static sections.MailRuStaticSite.homePage;
import static sections.MailRuStaticSite.sentPage;
import static sections.MailRuStaticSite.templatePage;

import org.testng.annotations.Test;

public class JdiTest extends JdiTestsBase {

  private final String addressee = "hmel25@bk.ru";
  private final String subject = "Elimination details";
  private final String body = "4815162342";
  private final String noDrafts = "У вас нет незаконченных\nили неотправленных писем";

  @Test
  public void createDraftAndSendMail() {
    homePage.checkOpened();
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    baseMailPage.saveMailAsDraft();
    baseMailPage.closeMessageWindow();
    draftsPage.shouldBeOpened();
    draftsPage.draftsAddressee.isNotEmpty();
    draftsPage.draftsSubjectsList.get(0).has().text(subject);
    draftsPage.selectDraftAndSendMail();
    sentPage.shouldBeOpened();
    sentPage.mailsList.get(0).has().text(containsString(subject));
    draftsPage.shouldBeOpened();
    draftsPage.noDraftsMessage.is().text(noDrafts);
  }

  @Test
  public void saveAsTemplateAndSendMail() {
    homePage.checkOpened();
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    baseMailPage.saveLetterAsTemplate();
    templatePage.shouldBeOpened();
    templatePage.openTemplate();
    templatePage.sendMail();
    sentPage.shouldBeOpened();
    sentPage.mailsList.get(0).has().text(containsString(subject));
  }
}
