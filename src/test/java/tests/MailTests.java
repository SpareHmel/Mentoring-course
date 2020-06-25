package tests;

import static driver_manager.DriverManager.getDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import factory.Mail;
import factory.MailFactory;
import org.testng.annotations.Test;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.SentPage;
import pages.TemplatePage;

public class MailTests extends BaseMailTest {

  private final String subject = "Elimination details";
  private final String title = "Входящие - Почта Mail.ru";
  private final String noDrafts = "У вас нет незаконченных\nили неотправленных писем";

  @Test
  public void createDraftAndSendMail() {
    //Assert, that the login is successful.
    isTitlePresentedWithText(title);
    //Create a new mail (fill addressee, subject and body fields).
    baseMailPage = new BaseMailPage(getDriver());
    baseMailPage.startWritingLetter();
    Mail mail = MailFactory.createSimpleMail();
    baseMailPage.fillInMailFields(mail);
    //Save the mail as a draft.
    baseMailPage.saveMailAsDraft();
    baseMailPage.closeMessageWindow();
    //Verify the draft content (addressee, subject and body – should be the same as in 3).
    draftsPage = new DraftsPage(getDriver());
    draftsPage.get();
    assertTrue(draftsPage.isDraftsAddresseeDisplayed());
    assertTrue(draftsPage.getDraftsSubjectText().contains(subject));
    //Send the mail.
    draftsPage.selectDraftAndSendMail();
    //Verify, that the mail is in ‘Sent’ folder
    sentPage = new SentPage(getDriver());
    sentPage.get();
    assertTrue(sentPage.getMailDetailsText().contains(subject));
    //Verify, that the mail disappeared from ‘Drafts’ folder.
    draftsPage.get();
    assertEquals(draftsPage.getNoDraftsMessageText(), noDrafts);
  }

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
  public void saveDraftThenDelete() {
    isTitlePresentedWithText(title);
    baseMailPage = new BaseMailPage(getDriver());
    baseMailPage.startWritingLetter();
    Mail mail = MailFactory.createMailWithoutSubject();
    baseMailPage.fillInMailFields(mail);
    baseMailPage.saveMailAsDraft();
    draftsPage = new DraftsPage(getDriver());
    draftsPage.get();
    draftsPage.deleteDraft();
    assertEquals(draftsPage.getNoDraftsMessageText(), noDrafts);
  }

  @Test
  public void moveToAddresseeCopy() {
    isTitlePresentedWithText(title);
    baseMailPage = new BaseMailPage(getDriver());
    baseMailPage.startWritingLetter();
    Mail mail = MailFactory.createSimpleMail();
    baseMailPage.fillInMailFields(mail);
    baseMailPage.moveAddresseeToCopy();
    assertTrue(baseMailPage.checkAddresseeVisibility());
    baseMailPage.closeMessageWindow();
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
