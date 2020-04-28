import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.SentPage;
import pages.TemplatePage;

public class MailTests extends BaseMailTest {

  private final String addressee = "hmel25@bk.ru";
  private final String subject = "Elimination details";
  private final String body = "4815162342";

  @Test
  public void createDraftAndSendMail() {
    //Assert, that the login is successful.
    isTitlePresentedWithText("Входящие - Почта Mail.ru");
    //Create a new mail (fill addressee, subject and body fields).
    baseMailPage = new BaseMailPage(driver);
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    //Save the mail as a draft.
    baseMailPage.saveMailAsDraft();
    baseMailPage.closeMessageWindow();
    //Verify the draft content (addressee, subject and body – should be the same as in 3).
    draftsPage = new DraftsPage(driver);
    draftsPage.openPage();
    assertTrue(draftsPage.isDraftsAddresseeDisplayed());
    assertEquals(draftsPage.getDraftsSubjectText(), subject);
    //Send the mail.
    draftsPage.selectDraftAndSendMail();
    //Verify, that the mail is in ‘Sent’ folder
    sentPage = new SentPage(driver);
    sentPage.openPage();
    assertTrue(sentPage.getMailDetailsText().contains(subject));
    //Verify, that the mail disappeared from ‘Drafts’ folder.
    draftsPage.openPage();
    assertEquals(draftsPage.getNoDraftsMessageText(), "У вас нет незаконченных\nили неотправленных писем");
  }

  @Test
  public void saveAsTemplateAndSendMail() {
    isTitlePresentedWithText("Входящие - Почта Mail.ru");
    baseMailPage = new BaseMailPage(driver);
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    baseMailPage.saveLetterAsTemplate();
    templatePage = new TemplatePage(driver);
    templatePage.openPage();
    templatePage.openTemplate();
    templatePage.sendMail();
    sentPage = new SentPage(driver);
    sentPage.openPage();
    assertTrue(sentPage.getMailDetailsText().contains(subject));
  }

  @Test
  public void saveDraftThenDelete() {
    isTitlePresentedWithText("Входящие - Почта Mail.ru");
    baseMailPage = new BaseMailPage(driver);
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    baseMailPage.saveMailAsDraft();
    draftsPage = new DraftsPage(driver);
    draftsPage.openPage();
    draftsPage.deleteDraft();
  }
}
